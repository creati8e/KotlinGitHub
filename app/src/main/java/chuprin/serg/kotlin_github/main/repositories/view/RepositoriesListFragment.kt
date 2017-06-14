package chuprin.serg.kotlin_github.main.repositories.view

import chuprin.serg.kotlin_github.KotApplication
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.main.MainComponent
import chuprin.serg.kotlin_github.main.MainModule
import chuprin.serg.kotlin_github.main.repositories.presenter.RepositoriesListPresenter
import chuprin.serg.kotlin_github.main.view.ListFragment
import org.jetbrains.anko.toast
import javax.inject.Inject

class RepositoriesListFragment : ListFragment<GithubRepositoryEntity, RepositoryAdapter>(), RepositoriesListView {

    companion object {
        const val BUNDLE_USER_LOGIN = "BUNDLE_USER_LOGIN"
    }

    @Inject lateinit var presenter: RepositoriesListPresenter

    override fun createComponent() = KotApplication.component.mainComponent(MainModule(arguments))

    override fun createAdapter(): RepositoryAdapter = RepositoryAdapter()

    override fun componentClass(): Class<*> = MainComponent::class.java

    override fun onItemClicked(model: GithubRepositoryEntity, pos: Int) {
        activity.toast("Clicked: " + model.name)
    }

    fun applyFilter(id: Int) {
        presenter.applyFilter(id)
    }

}