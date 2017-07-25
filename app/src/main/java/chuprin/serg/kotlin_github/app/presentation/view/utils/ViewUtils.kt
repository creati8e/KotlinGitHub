package chuprin.serg.kotlin_github.app.presentation.view.utils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import chuprin.serg.kotlin_github.app.domain.pagintation.ScrollEvent
import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView
import io.reactivex.Observable
import org.jetbrains.anko.bundleOf


fun View.visibility(visible: Boolean) {
    visibility = when (visible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}


inline fun <reified T : Fragment> instanceOf(vararg params: Pair<String, Any>): T
        = T::class.java.newInstance().apply {
    arguments = bundleOf(*params)
}

fun Bundle.putAll(vararg bundles: Bundle?): Bundle = Bundle()
        .apply {
            bundles.filterNotNull().forEach { putAll(it) }
        }

fun RecyclerView.paginate(): Observable<ScrollEvent> {
    return RxRecyclerView.scrollEvents(this)
            .startWith(RecyclerViewScrollEvent.create(this, 0, 0))
            .distinctUntilChanged({ t1, t2 -> t1.dy() == t2.dy() })
            .map {
                val pos = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                ScrollEvent(pos, adapter.itemCount)
            }
}




