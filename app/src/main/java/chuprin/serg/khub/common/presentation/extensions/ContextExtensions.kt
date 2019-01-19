package chuprin.serg.khub.common.presentation.extensions

import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat


/**
 * @author Sergey Chuprin, Ruslan Arslanov
 */
fun Context.getColorInt(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)

// region Dimens conversion
/**
 * Pass desirable density-independent value to obtain its pixel representation,
 * e.g.: context.dpToPix(16) means convert 16dp to corresponding amount of pixels.
 */
fun Context.dpToPx(dpValue: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, resources.displayMetrics)
}

fun Context.dpToPx(dpValue: Int): Int = (dpValue * resources.displayMetrics.density).toInt()

/**
 * Pass desirable pixel value to obtain its density-independent representation,
 * e. g.: context.pixToDip(360) means translate 360px to corresponding amount of dp.
 * The result can be used, for instance, to compare view size with screen width or height.
 */
fun Context.pxToDp(pxValue: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pxValue, resources.displayMetrics)
}

fun Context.pxToDp(pxValue: Int): Int {
    return TypedValue
        .applyDimension(TypedValue.COMPLEX_UNIT_PX, pxValue.toFloat(), resources.displayMetrics)
        .toInt()
}

fun Context.getDimenPxFloat(@DimenRes dimenResId: Int): Float {
    return resources.getDimensionPixelSize(dimenResId).toFloat()
}

fun Context.getDimenPxInt(@DimenRes dimenResId: Int): Int {
    return resources.getDimensionPixelSize(dimenResId)
}

fun Context.getDimenDpFloat(@DimenRes dimenResId: Int): Float {
    return pxToDp(resources.getDimension(dimenResId))
}

// endregion

// region Screen orientation

val Context.isPortrait: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

val Context.isLandscape: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

val Context.orientation: Int
    get() = resources.configuration.orientation

// endregion

@ColorInt
fun Context.getAttributeColor(@AttrRes attrRes: Int): Int {
    val outValue = TypedValue()
    theme.resolveAttribute(attrRes, outValue, true)
    return outValue.data
}

// region Miscellaneous

fun Context.getKeyboard(): InputMethodManager {
    return getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
}

// endregion

inline val Context.layoutInflater: android.view.LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as android.view.LayoutInflater