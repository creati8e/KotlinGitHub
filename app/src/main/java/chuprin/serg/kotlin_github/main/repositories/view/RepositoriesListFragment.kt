package chuprin.serg.kotlin_github.main.repositories.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import chuprin.serg.kotlin_github.KotApplication
import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.presentation.view.utils.visibility
import chuprin.serg.kotlin_github.main.MainComponent
import chuprin.serg.kotlin_github.main.MainModule
import chuprin.serg.kotlin_github.main.repositories.presenter.RepositoriesListPresenter
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.toast
import serg.chuprin.adapter.MultiViewAdapter
import serg.chuprin.mvp_core.android.MvpFragment
import javax.inject.Inject

class RepositoriesListFragment : MvpFragment<RepositoriesListPresenter>(), RepositoriesListView {

    companion object {
        const val BUNDLE_USER_LOGIN = "BUNDLE_USER_LOGIN"
    }

    private val adapter: MultiViewAdapter = MultiViewAdapter()

    @Inject lateinit var presenter: RepositoriesListPresenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.registerRenderer(RepositoryRenderer())
        adapter.clickListener = { model, _, _ -> onAdapterClick(model) }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun onAdapterClick(model: Any) = showMessage(R.string.abc_action_bar_home_description)

    override fun getLayoutRes(): Int = R.layout.fragment_list

    override fun showData(data: List<GithubRepositoryEntity>) = adapter.setItems(data)

    override fun addData(list: List<GithubRepositoryEntity>) = adapter.addItems(list)

    override fun showProgress(visible: Boolean) = progress.visibility(visible)

    override fun showMessage(message: Int) = activity.toast(message)

    override fun createComponent() = KotApplication.component.mainComponent(MainModule(arguments))

    override fun componentClass(): Class<*> = MainComponent::class.java

    fun applyFilter(id: Int) {
        presenter.applyFilter(id)
    }

}