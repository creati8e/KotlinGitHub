// Copyright 2015 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package chuprin.serg.kotlin_github.main.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList


/**
 * Helper class for Custom Tabs.
 */
object CustomTabsHelper {
    private val TAG = "CustomTabsHelper"
    private val STABLE_PACKAGE = "com.android.chrome"
    private val BETA_PACKAGE = "com.chrome.beta"
    private val DEV_PACKAGE = "com.chrome.dev"
    private val LOCAL_PACKAGE = "com.google.android.apps.chrome"
    private val ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService"

    private var packageNameToUse: String? = null

    /**
     * Goes through all apps that handle VIEW intents and have a warmup service. Picks
     * the one chosen by the user if there is one, otherwise makes a best effort to return a
     * valid package name.

     * This is **not** threadsafe.

     * @param context [Context] to use for accessing [PackageManager].
     * *
     * @return The package name recommended to use for connecting to custom tabs related components.
     */
    fun getPackageNameToUse(context: Context): String? {
        if (packageNameToUse != null) return packageNameToUse as String

        val pm = context.packageManager
        // Get default VIEW intent handler.
        val activityIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com"))
        val defaultViewHandlerInfo = pm.resolveActivity(activityIntent, 0)
        var defaultViewHandlerPackageName: String? = null
        if (defaultViewHandlerInfo != null) {
            defaultViewHandlerPackageName = defaultViewHandlerInfo.activityInfo.packageName
        }

        // Get all apps that can handle VIEW intents.
        val resolvedActivityList = pm.queryIntentActivities(activityIntent, 0)
        val packagesSupportingCustomTabs = ArrayList<String>()
        for (info in resolvedActivityList) {
            val serviceIntent = Intent()
            serviceIntent.action = ACTION_CUSTOM_TABS_CONNECTION
            serviceIntent.`package` = info.activityInfo.packageName
            if (pm.resolveService(serviceIntent, 0) != null) {
                packagesSupportingCustomTabs.add(info.activityInfo.packageName)
            }
        }

        // Now packagesSupportingCustomTabs contains all apps that can handle both VIEW intents
        // and service calls.
        if (packagesSupportingCustomTabs.isEmpty()) {
            packageNameToUse = null
        } else if (packagesSupportingCustomTabs.size == 1) {
            packageNameToUse = packagesSupportingCustomTabs[0]
        } else if (!TextUtils.isEmpty(defaultViewHandlerPackageName)
                && !hasSpecializedHandlerIntents(context, activityIntent)
                && packagesSupportingCustomTabs.contains(defaultViewHandlerPackageName)) {
            packageNameToUse = defaultViewHandlerPackageName
        } else if (packagesSupportingCustomTabs.contains(STABLE_PACKAGE)) {
            packageNameToUse = STABLE_PACKAGE
        } else if (packagesSupportingCustomTabs.contains(BETA_PACKAGE)) {
            packageNameToUse = BETA_PACKAGE
        } else if (packagesSupportingCustomTabs.contains(DEV_PACKAGE)) {
            packageNameToUse = DEV_PACKAGE
        } else if (packagesSupportingCustomTabs.contains(LOCAL_PACKAGE)) {
            packageNameToUse = LOCAL_PACKAGE
        }
        return packageNameToUse
    }

    /**
     * Used to check whether there is a specialized handler for a given intent.
     * @param intent The intent to check with.
     * *
     * @return Whether there is a specialized handler for the given intent.
     */
    private fun hasSpecializedHandlerIntents(context: Context, intent: Intent): Boolean {
        try {
            val pm = context.packageManager
            val handlers = pm.queryIntentActivities(
                    intent,
                    PackageManager.GET_RESOLVED_FILTER)
            if (handlers == null || handlers.size == 0) {
                return false
            }
            for (resolveInfo in handlers) {
                val filter = resolveInfo.filter ?: continue
                if (filter.countDataAuthorities() == 0 || filter.countDataPaths() == 0) continue
                if (resolveInfo.activityInfo == null) continue
                return true
            }
        } catch (e: RuntimeException) {
            Log.e(TAG, "Runtime exception while getting specialized handlers")
        }

        return false
    }

    fun openInCustomTabOrBrowser(activity: Activity, uri: Uri) {
        val pkg = CustomTabsHelper.getPackageNameToUse(activity)
        if (pkg != null) {
            val i = CustomTabsIntent.Builder()
                    .build()
            i.intent.`package` = pkg
            i.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            i.launchUrl(activity, uri)
        } else {
            launchBrowser(activity, uri, Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    fun launchBrowser(context: Context, uri: Uri, flags: Int) {
        val intent = createBrowserIntent(context, uri)
        if (intent != null) {
            intent.addFlags(flags)
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "No browser found", Toast.LENGTH_LONG).show()
        }
    }

    private fun createBrowserIntent(context: Context, uri: Uri): Intent? {
        val dummyUri = uri.buildUpon().authority("www.somedummy.com").build()
        val browserIntent = Intent(Intent.ACTION_VIEW, dummyUri)
                .addCategory(Intent.CATEGORY_BROWSABLE)
        return createActivityChooserIntent(context, browserIntent, uri)
    }

    private fun createActivityChooserIntent(context: Context, intent: Intent, uri: Uri): Intent? {
        val pm: PackageManager = context.packageManager
        val activities: List<ResolveInfo> = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        val chooserIntents: ArrayList<Intent> = ArrayList()

        Collections.sort(activities, ResolveInfo.DisplayNameComparator(pm))
        activities.map { it.activityInfo }
                .filter { it.enabled && it.exported && it.packageName != context.packageName }
                .mapTo(chooserIntents) {
                    Intent(intent).apply {
                        `package` = it.packageName;
                        setDataAndType(uri, intent.type)
                    }
                }
        if (chooserIntents.isEmpty()) return null
        val lastIntent: Intent = chooserIntents.removeAt(chooserIntents.size - 1)
        if (chooserIntents.isEmpty()) return lastIntent

        return Intent.createChooser(lastIntent, null)
                .apply { putExtra(Intent.EXTRA_INITIAL_INTENTS, chooserIntents.toArray()) }
    }

}