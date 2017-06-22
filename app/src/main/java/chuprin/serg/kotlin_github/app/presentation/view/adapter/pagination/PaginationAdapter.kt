package chuprin.serg.kotlin_github.app.presentation.view.adapter.pagination

import chuprin.serg.kotlin_github.app.presentation.view.adapter.multiviewadapter.MultiViewAdapter

class PaginationAdapter(items: MutableList<Any> = mutableListOf<Any>()) : MultiViewAdapter(items) {

    fun setLoadingMoreVisibility(visible: Boolean) = when (visible) {
        true -> addItemIfNotPresent(ProgressModel())
        false -> removeLast(ProgressModel::class.java)
    }

    fun setNetworkErrorVisibility(visible: Boolean) = when (visible) {
        true -> addItemIfNotPresent(NetworkErrorModel())
        false -> removeLast(NetworkErrorModel::class.java)
    }

}