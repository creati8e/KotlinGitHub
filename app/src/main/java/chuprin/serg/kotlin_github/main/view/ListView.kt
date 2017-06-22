package chuprin.serg.kotlin_github.main.view

import chuprin.serg.kotlin_github.app.presentation.view.ProgressView

interface ListView<in MODEL> : ProgressView {

    fun showData(data: List<MODEL>)

    fun addData(list: List<MODEL>)

}