package chuprin.serg.kotlin_github.app.presentation.view.adapter.pagination

import chuprin.serg.kotlin_github.R
import serg.chuprin.adapter.ViewHolder
import serg.chuprin.adapter.ViewRenderer

class ProgressRenderer : ViewRenderer<ProgressModel, ViewHolder>() {

    override fun type(): Int = R.layout.list_item_progress

    override fun isViewForType(model: Any): Boolean = model is ProgressModel

}