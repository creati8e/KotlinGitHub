package chuprin.serg.kotlin_github.main.repositories.presenter

import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.domain.repositories.RepositoriesInteractor
import chuprin.serg.kotlin_github.app.presentation.presenter.observeWithProgress
import chuprin.serg.kotlin_github.main.repositories.view.RepositoriesListView
import io.reactivex.Observable
import serg.chuprin.mvp_core.MvpPresenter
import serg.chuprin.mvp_core.annotations.InjectViewState
import javax.inject.Inject

@InjectViewState
class RepositoriesListPresenter
@Inject constructor(private val interactor: RepositoriesInteractor,
                    private val userLogin: String) : MvpPresenter<RepositoriesListView>() {

    override fun onViewAttached() = when {
        userLogin.isEmpty() -> getData(interactor.getAllRepositories())
        else -> getData(interactor.getUserRepositories(userLogin))
    }

    fun applyFilter(id: Int) {
        when (id) {
            R.id.revealMenuForks -> getData(interactor.getUserForkedRepositories(userLogin))
            R.id.revealMenuOwn -> getData(interactor.getUserOwnRepositories(userLogin))
            R.id.revealMenuAll -> getData(interactor.getUserRepositories(userLogin))
        }
    }

    private fun getData(observable: Observable<List<GithubRepositoryEntity>>) {
        subscribeView(observable
                .observeWithProgress(view)
                .subscribe({ view.showData(it) }, { it.printStackTrace() }))
    }
}