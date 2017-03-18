package chuprin.serg.kotlin_github.main.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import chuprin.serg.kotlin_github.R.layout.fragment_list
import chuprin.serg.kotlin_github.app.mvp.MvpPresenter
import chuprin.serg.kotlin_github.app.mvp.view.MvpFragment
import chuprin.serg.kotlin_github.app.presentation.view.BaseAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.toast

abstract class ListFragment<MODEL, ADAPTER : BaseAdapter<MODEL>>
    : MvpFragment<MvpPresenter<*>>(fragment_list), ListView<MODEL> {

    protected lateinit var adapter: ADAPTER

    override fun onViewCreated(view: View?, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
        adapter = createAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter.clickCallback = { model: MODEL, i: Int -> onItemClicked(model, i) }
    }

    override fun showProgress(visible: Boolean) {
        progress.visibility = when (visible) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun showData(data: List<MODEL>) {
        adapter.setData(data)
    }

    override fun showMessage(message: Int) = activity.toast(message)

    override fun getContext(): Context = activity.applicationContext

    abstract fun onItemClicked(model: MODEL, pos: Int)

    abstract fun createAdapter(): ADAPTER


}