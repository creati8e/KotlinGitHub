package chuprin.serg.khub.common.presentation.extensions

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 * @author Sergey Chuprin
 */

const val TAG = "TAG"

fun DialogFragment.inflateCustomView(@LayoutRes layoutRes: Int): View {
    return requireActivity().layoutInflater.inflate(layoutRes, null)
}

inline fun <reified D : DialogFragment> dismissDialog(fragmentManager: FragmentManager) {
    val tag = "${D::class.java.simpleName}_$TAG"
    (fragmentManager.findFragmentByTag(tag) as? DialogFragment)?.dismiss()
}

inline fun <reified D : DialogFragment> showDialog(
    fragmentManager: FragmentManager,
    arguments: Parcelable? = null
) {
    val clazz = D::class.java
    val className = clazz.simpleName

    val tag = "${className}_$TAG"

    val fragment = fragmentManager.findFragmentByTag(tag) ?: clazz.newInstance()
    if (fragment is DialogFragment && !fragment.isAdded) {
        if (arguments != null) {
            val argKey = "${className}_$DIALOG_ARG_KEY"
            fragment.arguments = bundleOf(argKey to arguments)
        }
        fragment.show(fragmentManager, tag)
    }
}

fun Parcelable.toFragmentArgs(): Bundle = bundleOf(FRAGMENT_ARG_KEY to this)