package chuprin.serg.kotlin_github.main.repositories.view

import android.os.Bundle
import chuprin.serg.kotlin_github.KotApplication
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.main.MainModule
import chuprin.serg.kotlin_github.main.repositories.presenter.RepositoriesListPresenter
import chuprin.serg.kotlin_github.main.view.ListFragment
import org.jetbrains.anko.toast
import javax.inject.Inject

class RepositoriesListFragment : ListFragment<GithubRepositoryEntity, RepositoryAdapter>(), RepositoriesListView {

    @Inject lateinit var presenter: RepositoriesListPresenter

    companion object {
        fun newInstance(): RepositoriesListFragment = RepositoriesListFragment()
    }

    override fun createComponent(savedState: Bundle?): Any {
        return KotApplication.component.mainComponent(MainModule(savedState))
    }

    override fun createAdapter(): RepositoryAdapter = RepositoryAdapter()

    override fun onItemClicked(model: GithubRepositoryEntity, pos: Int) {
        activity.toast("Clicked: " + model.name)
    }

}