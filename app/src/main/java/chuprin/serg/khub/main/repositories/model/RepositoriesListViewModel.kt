package chuprin.serg.khub.main.repositories.model

import chuprin.serg.khub.R
import chuprin.serg.khub.common.data.entity.GithubRepositoryEntity
import chuprin.serg.khub.common.domain.interactor.RepositoryInteractor
import chuprin.serg.khub.common.presentation.livedata.MutableLiveDataDelegate
import chuprin.serg.khub.common.presentation.viewmodel.BaseViewModel
import com.github.ajalt.timberkt.Timber
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * @author Sergey Chuprin
 */
class RepositoriesListViewModel(
    private val userLogin: String,
    private val interactor: RepositoryInteractor
) : BaseViewModel() {

    val listLiveData = MutableLiveDataDelegate<List<GithubRepositoryEntity>>()

    private var reposDisposable: Disposable? = null

    init {
        if (userLogin.isEmpty()) {
            interactor.getAllRepositories().observe()
        } else {
            interactor.getUserRepositories(userLogin).observe()
        }
    }

    fun applyFilter(id: Int) {
        when (id) {
            R.id.revealMenuAll -> interactor.getUserRepositories(userLogin).observe()
            R.id.revealMenuOwn -> interactor.getUserOwnRepositories(userLogin).observe()
            R.id.revealMenuForks -> interactor.getUserForkedRepositories(userLogin).observe()
        }
    }

    private fun Observable<List<GithubRepositoryEntity>>.observe() {
        reposDisposable?.dispose()
        reposDisposable = subscribe(listLiveData::setter, Timber::e).untilCleared()
    }

}