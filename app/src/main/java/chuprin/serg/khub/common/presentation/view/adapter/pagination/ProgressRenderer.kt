package chuprin.serg.khub.common.presentation.view.adapter.pagination

import chuprin.serg.khub.R
import serg.chuprin.adapter.ViewHolder
import serg.chuprin.adapter.ViewRenderer

class ProgressRenderer : ViewRenderer<ProgressModel, ViewHolder>() {

    override fun type(): Int = R.layout.list_item_progress

    override fun isViewForType(model: Any): Boolean = model is ProgressModel

}