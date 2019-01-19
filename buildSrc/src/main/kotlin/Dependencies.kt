import BuildScript.Versions.navigationVer
import Libraries.Android.Versions.appCompatVer
import Libraries.Android.Versions.constraintVer
import Libraries.Android.Versions.coordinatorVer
import Libraries.Android.Versions.customTabsVer
import Libraries.Android.Versions.designVer
import Libraries.Android.Versions.drawerLayoutVer
import Libraries.Android.Versions.ktxVer
import Libraries.Android.Versions.lifecycleExtVer
import Libraries.Android.Versions.viewModelVer
import Libraries.Android.Versions.xVer
import Libraries.Network.Versions.retrofitVer
import Libraries.Rx.Versions.rxAndroidVer
import Libraries.Rx.Versions.rxBindingVer
import Libraries.Rx.Versions.rxJavaVer
import Libraries.Rx.Versions.rxRelayVer

object BuildScript {

    object Versions {
        const val realmVer = "3.5.0"
        const val kotlinVer = "1.3.11"
        const val androidVer = "3.3.0"
        const val navigationVer = "1.0.0-alpha09"
    }

    object Plugins {
        const val appBadge = "gradle.plugin.app-badge:plugin:1.0.1"
        const val realm = "io.realm:realm-gradle-plugin:${Versions.realmVer}"
        const val android = "com.android.tools.build:gradle:${Versions.androidVer}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVer}"
        const val graphGenerator = "com.vanniktech:gradle-dependency-graph-generator-plugin:0.5.0"
        const val navigationSafeArgs =
            "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVer}"
    }

}

object Libraries {

    private const val ankoVer = "0.10.8"

    const val anko = "org.jetbrains.anko:anko-commons:$ankoVer"

    const val rxPrefs = "com.afollestad:rxkprefs:1.2.1"
    const val timber = "com.github.ajalt:timberkt:1.5.1"
    const val jodaTime = "net.danlew:android.joda:2.10.1.1"
    const val adapter = "serg.chuprin:multiviewadapter:1.0"
    const val glide = "com.github.bumptech.glide:glide:3.7.0"
    const val javaxAnnotations = "javax.inject:javax.inject:1"
    const val circleImageView = "de.hdodenhof:circleimageview:2.1.0"
    const val mp4Parser = "com.googlecode.mp4parser:isoparser:1.1.22"
    const val realmExtensions = "com.github.vicpinm:krealmextensions:1.0.9"
    const val adapterDelegates = "com.hannesdorfmann:adapterdelegates4:4.0.0"
    const val permissions = "com.github.quickpermissions:quickpermissions-kotlin:0.4.0"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${BuildScript.Versions.kotlinVer}"

    object Android {

        object Versions {
            const val xVer = "1.0.0"
            const val ktxVer = "1.0.1"
            const val designVer = "1.0.0"
            const val appCompatVer = "1.0.2"
            const val constraintVer = "1.1.3"
            const val viewModelVer = "2.0.0"
            const val customTabsVer = "28.0.0"
            const val lifecycleExtVer = "2.0.0"
            const val drawerLayoutVer = "1.0.0"
            const val coordinatorVer = "1.0.0"
        }

        const val customTabs = "com.android.support:customtabs:$customTabsVer"
        const val constraint = "androidx.constraintlayout:constraintlayout:$constraintVer"
        const val drawerLayout = "androidx.drawerlayout:drawerlayout:$drawerLayoutVer"
        const val coordinator = "androidx.coordinatorlayout:coordinatorlayout:$coordinatorVer"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$viewModelVer"
        const val viewModelCompiler = "androidx.lifecycle:lifecycle-compiler:$viewModelVer"

        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleExtVer"

        const val fragment = "androidx.fragment:fragment-ktx:$xVer"
        const val appcompat = "androidx.appcompat:appcompat:$appCompatVer"
        const val design = "com.google.android.material:material:$designVer"
        const val recyclerView = "androidx.recyclerview:recyclerview:$xVer"

        const val ktx = "androidx.core:core-ktx:${ktxVer}"

        const val navigationUi = "android.arch.navigation:navigation-ui-ktx:$navigationVer"
        const val navigation = "android.arch.navigation:navigation-fragment-ktx:$navigationVer"
        const val navigationRuntime = "android.arch.navigation:navigation-runtime:$navigationVer"

    }

    object Dagger {
        private const val daggerVer = "2.11"

        const val lib = "com.google.dagger:dagger:$daggerVer"
        const val compiler = "com.google.dagger:dagger-compiler:$daggerVer"
    }

    object Rx {

        object Versions {
            const val rxJavaVer = "2.2.5"
            const val rxRelayVer = "2.1.0"
            const val rxAndroidVer = "2.1.0"
            const val rxBindingVer = "3.0.0-alpha2"
        }

        const val rxJava = "io.reactivex.rxjava2:rxjava:$rxJavaVer"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxAndroidVer"

        const val rxRelay = "com.jakewharton.rxrelay2:rxrelay:$rxRelayVer"

        const val rxBindingCore = "com.jakewharton.rxbinding3:rxbinding-core:$rxBindingVer"
        const val rxBindingDesign = "com.jakewharton.rxbinding3:rxbinding-material:$rxBindingVer"
        const val rxBindingAppCompat =
            "com.jakewharton.rxbinding3:rxbinding-appcompat:$rxBindingVer"
        const val rxBindingRecycler =
            "com.jakewharton.rxbinding3:rxbinding-recyclerview:$rxBindingVer"
    }

    object Network {
        object Versions {
            const val retrofitVer = "2.5.0"
        }

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVer"
        const val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVer"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVer"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:3.10.0"
    }

    object Mvp {
        private const val ver = "1.0.1"

        const val core = "serg.chuprin:mvp-core:$ver"
        const val android = "serg.chuprin:mvp-core-android:$ver"
        const val compiler = "serg.chuprin:mvp-core-processor:$ver"
    }

}