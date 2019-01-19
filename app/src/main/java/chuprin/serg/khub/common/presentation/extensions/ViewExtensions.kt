package chuprin.serg.khub.common.presentation.extensions

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.CompoundButton
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chuprin.serg.khub.common.domain.pagintation.ScrollEvent
import com.jakewharton.rxbinding3.recyclerview.RecyclerViewScrollEvent
import com.jakewharton.rxbinding3.recyclerview.scrollEvents
import io.reactivex.Observable
import org.jetbrains.anko.bundleOf
import kotlin.math.roundToInt

/**
 * @author Ruslan Arslanov, Sergey Chuprin
 */

fun View.visibility(visible: Boolean) {
    visibility = when (visible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

inline fun <reified T : Fragment> instanceOf(vararg params: Pair<String, Any>): T {
    return T::class.java.newInstance().apply {
        arguments = bundleOf(*params)
    }
}

fun Bundle.putAll(vararg bundles: Bundle?): Bundle {
    return Bundle().apply { bundles.filterNotNull().forEach { putAll(it) } }
}

fun RecyclerView.observeScrollEvents(): Observable<ScrollEvent> {
    return scrollEvents()
        .startWith(RecyclerViewScrollEvent(this, 0, 0))
        .distinctUntilChanged { t1, t2 -> t1.dy == t2.dy }
        .map {
            val pos = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            ScrollEvent(pos, adapter?.itemCount ?: 0)
        }

}

const val VIEW_TAG_IGNORE_SELECTION = "TAG_IGNORE_SELECTION"

inline fun View.onClick(crossinline block: () -> Unit) = setOnClickListener { block.invoke() }

inline fun View.onViewClick(crossinline block: (View) -> Unit) {
    setOnClickListener { block.invoke(it) }
}

inline fun CompoundButton.onChecked(crossinline block: (isChecked: Boolean) -> Unit) {
    setOnCheckedChangeListener { _, isChecked -> block.invoke(isChecked) }
}

// region Visibility.

fun View.makeGone() {
    if (isGone) return
    visibility = View.GONE
}

fun View.makeVisible() {
    if (isVisible) return
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    if (isInvisible) return
    visibility = View.INVISIBLE
}

fun View.makeVisibleOrGone(visible: Boolean) {
    if (visible) {
        makeVisible()
    } else {
        makeGone()
    }
}

fun View.makeVisibleOrInvisible(visible: Boolean) {
    if (visible) {
        makeVisible()
    } else {
        makeInvisible()
    }
}

// endregion

// region Layout params.

val View.marginParams: ViewGroup.MarginLayoutParams
    get() = this.layoutParams as ViewGroup.MarginLayoutParams

fun View.updateMargins(
    start: Int = marginParams.marginStart,
    end: Int = marginParams.marginEnd,
    top: Int = marginParams.topMargin,
    bottom: Int = marginParams.bottomMargin
) {
    layoutParams = marginParams.apply {
        marginStart = start
        marginEnd = end
        topMargin = top
        bottomMargin = bottom
    }
}

fun View.updateLayoutWidth(width: Int): View {
    layoutParams?.let {
        it.width = width
        requestLayout()
    }
    return this
}

fun View.updateLayoutHeight(height: Int): View {
    layoutParams?.let {
        it.height = height
        requestLayout()
    }
    return this
}

fun View.updateLayoutParams(width: Int, height: Int): View {
    layoutParams?.let {
        it.width = width
        it.height = height
        requestLayout()
    }
    return this
}

fun View.updateWrapContent(): View {
    layoutParams?.let {
        it.width = ViewGroup.LayoutParams.WRAP_CONTENT
        it.height = ViewGroup.LayoutParams.WRAP_CONTENT
        requestLayout()
    }
    return this
}

fun View.updateWrapContentWidth(): View {
    layoutParams?.let {
        it.width = ViewGroup.LayoutParams.WRAP_CONTENT
        requestLayout()
    }
    return this
}

fun View.updateWrapContentHeight(): View {
    layoutParams?.let {
        it.height = ViewGroup.LayoutParams.WRAP_CONTENT
        requestLayout()
    }
    return this
}

fun View.applyMatchParent(): View {
    layoutParams?.let {
        it.width = ViewGroup.LayoutParams.MATCH_PARENT
        it.height = ViewGroup.LayoutParams.MATCH_PARENT
        requestLayout()
    }
    return this
}

fun View.applyMatchParentWidth(): View {
    layoutParams?.let {
        it.width = ViewGroup.LayoutParams.MATCH_PARENT
        requestLayout()
    }
    return this
}

fun View.applyMatchParentHeight(): View {
    layoutParams?.let {
        it.height = ViewGroup.LayoutParams.MATCH_PARENT
        requestLayout()
    }
    return this
}

fun View.setMargins(
    leftDip: Int? = null,
    topDip: Int? = null,
    rightDip: Int? = null,
    bottomDip: Int? = null
) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        leftDip?.run { leftMargin = context.dpToPx(this) }
        topDip?.run { topMargin = context.dpToPx(this) }
        rightDip?.run { rightMargin = context.dpToPx(this) }
        bottomDip?.run { bottomMargin = context.dpToPx(this) }
    }
}

fun ViewGroup.asSequence(): Sequence<View> = object : Sequence<View> {

    override fun iterator(): Iterator<View> = object : Iterator<View> {
        private var nextValue: View? = null
        private var done = false
        private var position: Int = 0

        override fun hasNext(): Boolean {
            if (nextValue == null && !done) {
                nextValue = getChildAt(position)
                position++
                if (nextValue == null) done = true
            }
            return nextValue != null
        }

        override fun next(): View {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            val view = nextValue
            nextValue = null
            return view!!
        }
    }
}

val ViewGroup.views: List<View>
    get() = asSequence().toList()

val ViewGroup.childrenRecursive: List<View>
    get() {
        return views.flatMap {
            when (it) {
                is ViewGroup -> it.childrenRecursive
                else -> listOf(it)
            }
        }
    }

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    (layoutParams as? T)?.let(block)
}

// endregion

// region Miscellaneous

val View.alphaInt: Int get() = (alpha * 255.0f).roundToInt()

fun View.hideKeyboard() {
    context.getKeyboard().hideSoftInputFromWindow(windowToken, 0)
}

fun ViewGroup.enableChangingTransition() {
    val transition = layoutTransition ?: LayoutTransition().also { layoutTransition = it }
    transition.enableTransitionType(LayoutTransition.CHANGING)
}

fun ViewGroup.disableLayoutTransitions() {
    layoutTransition = null
}

fun ViewGroup.inflate(@LayoutRes layoutResId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutResId, this, attachToRoot)
}

// endregion

fun CompoundButton.setCheckedSilently(isChecked: Boolean) {
    tag = VIEW_TAG_IGNORE_SELECTION
    this.isChecked = isChecked
    tag = null
}

val TextView.textStr get() = text.toString()

inline fun TextView.onDoneAction(crossinline block: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            block()
            true
        } else {
            false
        }
    }
}