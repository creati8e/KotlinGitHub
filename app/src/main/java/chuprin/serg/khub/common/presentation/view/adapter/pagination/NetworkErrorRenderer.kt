package chuprin.serg.khub.common.presentation.view.adapter.pagination

import chuprin.serg.khub.R
import kotlinx.android.synthetic.main.list_item_network_error.view.*
import serg.chuprin.adapter.Click
import serg.chuprin.adapter.LongClick
import serg.chuprin.adapter.ViewHolder
import serg.chuprin.adapter.ViewRenderer

class NetworkErrorRenderer : ViewRenderer<NetworkErrorModel, ViewHolder>() {

    override fun type(): Int = R.layout.list_item_network_error

    override fun onVhCreated(
        holder: ViewHolder,
        clickListener: Click?,
        longClickListener: LongClick?
    ) {
        holder.itemView.tryBtn.setOnClickListener {
            clickListener?.onClick(it, holder.layoutPosition)
        }
    }

    override fun isViewForType(model: Any): Boolean = model is NetworkErrorModel

}