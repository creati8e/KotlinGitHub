package chuprin.serg.kotlin_github.app.presentation.view.adapter.multiviewadapter

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class ViewRenderer<in M : Any, VH : ViewHolder> {

    open fun bindView(holder: VH, model: M) = Unit

    @Suppress("UNCHECKED_CAST")
    open fun createViewHolder(parent: ViewGroup, clickListener: ViewHolder.ClickCallback?): VH {
        return (ViewHolder(inflate(type(), parent)) as VH).also { onVhCreated(it, clickListener) }
    }

    protected open fun onVhCreated(holder: VH, clickListener: ViewHolder.ClickCallback?) = Unit

    private fun inflate(@LayoutRes layout: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layout, parent, false)
    }

    abstract fun isViewForType(model: Any): Boolean

    abstract fun type(): Int
}
