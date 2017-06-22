package chuprin.serg.kotlin_github.app.presentation.view.adapter.pagination

import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.app.presentation.view.adapter.multiviewadapter.ViewHolder
import chuprin.serg.kotlin_github.app.presentation.view.adapter.multiviewadapter.ViewRenderer
import kotlinx.android.synthetic.main.list_item_network_error.view.*

class NetworkErrorRenderer : ViewRenderer<NetworkErrorModel, ViewHolder>() {

    override fun type(): Int = R.layout.list_item_network_error

    override fun onVhCreated(holder: ViewHolder, clickListener: ViewHolder.ClickCallback?) {
        holder.itemView.tryBtn.setOnClickListener {
            clickListener?.onClick(it, holder.layoutPosition)
        }
    }

    override fun isViewForType(model: Any): Boolean = model is NetworkErrorModel

}