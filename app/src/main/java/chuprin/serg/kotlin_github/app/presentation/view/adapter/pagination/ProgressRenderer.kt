package chuprin.serg.kotlin_github.app.presentation.view.adapter.pagination

import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.app.presentation.view.adapter.multiviewadapter.ViewHolder
import chuprin.serg.kotlin_github.app.presentation.view.adapter.multiviewadapter.ViewRenderer

class ProgressRenderer : ViewRenderer<ProgressModel, ViewHolder>() {

    override fun type(): Int = R.layout.list_item_progress

    override fun isViewForType(model: Any): Boolean = model is ProgressModel

}