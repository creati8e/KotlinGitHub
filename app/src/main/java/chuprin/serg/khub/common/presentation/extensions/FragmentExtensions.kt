package chuprin.serg.khub.common.presentation.extensions

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

/**
 * @author Sergey Chuprin
 */

const val DIALOG_ARG_KEY = "DIALOG_ARG_KEY"
const val FRAGMENT_ARG_KEY = "FRAGMENT_ARG_KEY"

fun Fragment.setupToolbar(
    toolbarView: Toolbar,
    initializer: (ActionBar.() -> Unit)? = null
) {
    (activity as? AppCompatActivity)?.run {
        setSupportActionBar(toolbarView)
        supportActionBar?.let { initializer?.invoke(it) }
    }
}

// region Arguments.

/**
 * Convenient method for retrieving fragment args.
 * Example: val dialogsArgs by dialogArgs<SingleChoiceDialogArguments> { arguments }
 * [T] - argument class.
 */
inline fun <reified T : Parcelable> DialogFragment.dialogArgs(
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.NONE,
    noinline bundleProvider: (() -> Bundle?)? = null
): Lazy<T> {
    return lazy(mode) {
        requireArgs<T>("${this::class.java.simpleName}_$DIALOG_ARG_KEY", bundleProvider)
    }
}

/**
 * Convenient method for retrieving fragment args.
 * Example: val dialogsArgs by args<SingleChoiceDialogArguments> { arguments }
 * [T] - argument class.
 */
inline fun <reified T : Parcelable> Fragment.args(
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.NONE,
    noinline bundleProvider: (() -> Bundle?)? = null
): Lazy<T> {
    return lazy(mode) {
        requireArgs<T>(FRAGMENT_ARG_KEY, bundleProvider)
    }
}

inline fun <reified T : Parcelable> Fragment.requireArgs(
    argKey: String,
    noinline bundleProvider: (() -> Bundle?)? = null
): T {
    val bundle = bundleProvider?.invoke() ?: arguments
    val args = bundle?.getParcelable<T>(argKey)
    return requireNotNull(args) { "${T::class.java.name} not found in bundle" }
}

// endregion.

// region Keyboard.

val Fragment.inputMethodManager: InputMethodManager?
    get() = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

fun Fragment.hideSoftKeyboard() {
    inputMethodManager?.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
}

fun Fragment.showSoftKeyboard(editText: EditText?) {
    editText?.requestFocus()
    inputMethodManager?.showSoftInput(editText, 0)
}

// endregion