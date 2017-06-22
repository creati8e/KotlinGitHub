package chuprin.serg.kotlin_github.app.presentation.view.adapter.multiviewadapter

import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup


open class MultiViewAdapter(val items: MutableList<Any> = mutableListOf<Any>())
    : RecyclerView.Adapter<ViewHolder>(), ViewHolder.ClickCallback {

    private val renderers = SparseArray<ViewRenderer<Any, ViewHolder>>()
    private val handler = Handler()
    var clickListener: ((Any, View, Int) -> Unit)? = null

    //region RecyclerView impl

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return renderers.get(viewType).createViewHolder(parent, this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (viewRenderer, model) = getRendererForPosition(position)
        viewRenderer.bindView(holder, model)
    }

    override fun getItemViewType(position: Int): Int = getRendererForPosition(position).first.type()

    override fun getItemCount(): Int = items.size

    //endregion

    //region data management

    fun getItem(position: Int): Any = items[position]

    fun setItems(newItems: List<Any>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<Any>) {
        items.addAll(newItems)
        notifyInserted(newItems.size)
    }

    fun addItem(model: Any) {
        items.add(itemCount, model)
        notifyInserted(1)
    }

    fun addItemIfNotPresent(model: Any) {
        when {
            items.isEmpty() -> addItem(model)
            model.javaClass != items[items.lastIndex].javaClass -> addItem(model)
        }
    }

    fun removeItemAt(position: Int) {
        if (position >= 0 && position < items.size) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun removeLast(itemClass: Class<out Any>) {
        if (items.isNotEmpty() && items[items.lastIndex].javaClass == itemClass) {
            removeItemAt(items.lastIndex)
        }
    }

    //endregion

    @Suppress("UNCHECKED_CAST")
    fun registerRenderer(renderer: ViewRenderer<*, *>) {
        if (renderers.get(renderer.type()) == null) {
            renderers.put(renderer.type(), renderer as ViewRenderer<Any, ViewHolder>)
        } else {
            throw RuntimeException("ViewRenderer already exist with this type: " + renderer.type())
        }
    }

    private fun notifyInserted(count: Int) = itemCount.run {
        handler.post { notifyItemRangeInserted(this, this + count) }
    }

    private fun getRendererForPosition(position: Int) = getItem(position).run {
        (0 until renderers.size())
                .asSequence()
                .map { renderers[renderers.keyAt(it)] }
                .filter { it.isViewForType(this) }
                .first() to this
    }

    override fun onClick(view: View, position: Int) {
        clickListener?.invoke(getItem(position), view, position)
    }
}