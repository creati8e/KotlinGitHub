package chuprin.serg.kotlin_github.app.presentation.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

abstract class BaseAdapter<MODEL> : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    private var listData: List<MODEL> = ArrayList()
    var clickCallback: ((MODEL, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(getLayoutRes(), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = bindVh(holder, get(position), position)

    override fun getItemCount(): Int = listData.size

    abstract fun getLayoutRes(): Int

    fun get(position: Int): MODEL = listData[position]

    abstract fun bindVh(vh: ViewHolder, data: MODEL, position: Int)

    fun setData(data: List<MODEL>) {
        listData = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}

fun <MODEL, ADAPTER : BaseAdapter<MODEL>, VH : RecyclerView.ViewHolder> VH.setListener(view: View, adapter: ADAPTER): VH {
    view.setOnClickListener { adapter.clickCallback?.invoke(adapter.get(layoutPosition), layoutPosition) }
    return this
}

