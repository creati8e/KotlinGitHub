package chuprin.serg.kotlin_github.main.view

import chuprin.serg.kotlin_github.app.presentation.view.ProgressView

interface ListView<in P> : ProgressView {

    fun showData(data: List<P>)

    fun addData(list: List<P>)

}