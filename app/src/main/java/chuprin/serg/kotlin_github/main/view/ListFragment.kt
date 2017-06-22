package chuprin.serg.kotlin_github.main.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.app.presentation.view.adapter.BaseAdapter
import chuprin.serg.kotlin_github.app.presentation.view.utils.visibility
import kotlinx.android.synthetic.main.fragment_list.*
import mvp_core.MvpPresenter
import mvp_core.view.MvpFragment
import org.jetbrains.anko.toast

abstract class ListFragment<MODEL, ADAPTER : BaseAdapter<MODEL>>
    : MvpFragment<MvpPresenter<*>>(), ListView<MODEL> {

    protected lateinit var adapter: ADAPTER

    override fun onViewCreated(view: View?, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
        adapter = createAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter.clickCallback = { model: MODEL, i: Int -> onItemClicked(model, i) }
    }

    override fun showProgress(visible: Boolean) = progress.visibility(visible)

    override fun showData(data: List<MODEL>) = adapter.setData(data)

    override fun addData(list: List<MODEL>) = adapter.insert(list)

    override fun getLayoutRes(): Int = R.layout.fragment_list

    override fun showMessage(message: Int) = activity.toast(message)

    override fun getContext(): Context = activity.applicationContext

    abstract fun onItemClicked(model: MODEL, pos: Int)

    abstract fun createAdapter(): ADAPTER


}