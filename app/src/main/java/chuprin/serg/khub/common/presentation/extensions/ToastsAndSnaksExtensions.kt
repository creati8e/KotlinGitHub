package chuprin.serg.khub.common.presentation.extensions

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Ruslan Arslanov on 22/08/2017.
 */

// ------------------------ Toasts ---------------------- //

fun Context.shortToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.shortToast(@StringRes textResId: Int) {
    Toast.makeText(this, textResId, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.longToast(@StringRes textResId: Int) {
    Toast.makeText(this, textResId, Toast.LENGTH_LONG).show()
}

fun Fragment.shortToast(@StringRes textResId: Int) {
    context?.shortToast(textResId)
}

fun Fragment.shortToast(text: String) {
    context?.shortToast(text)
}

fun Fragment.longToast(@StringRes textResId: Int) {
    context?.longToast(textResId)
}

fun View.shortToast(@StringRes textResId: Int) {
    this.context.shortToast(textResId)
}

fun View.longToast(@StringRes textResId: Int) {
    this.context.longToast(textResId)
}

// ------------------------ Snacks ---------------------- //

fun View.shortSnack(@StringRes textResId: Int, snackContainer: ViewGroup? = null) {
    snack(textResId, Snackbar.LENGTH_SHORT, snackContainer).show()
}

fun View.longSnack(@StringRes textResId: Int, snackContainer: ViewGroup? = null) {
    snack(textResId, Snackbar.LENGTH_LONG, snackContainer).show()
}

fun View.indefiniteSnack(message: String, snackContainer: ViewGroup? = null): Snackbar {
    return snack(message, Snackbar.LENGTH_INDEFINITE, snackContainer)
}

private fun View.snack(
    @StringRes textResId: Int,
    duration: Int,
    snackContainer: ViewGroup? = null
): Snackbar {
    return snack(context.getString(textResId), duration, snackContainer)
}

private fun View.snack(
    message: String,
    duration: Int,
    snackContainer: ViewGroup? = null
): Snackbar {
    return Snackbar.make(snackContainer ?: this, message, duration)
}