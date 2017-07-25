package chuprin.serg.kotlin_github.main.users.presenter

import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.domain.pagintation.Paginator
import chuprin.serg.kotlin_github.app.domain.pagintation.ScrollEvent
import chuprin.serg.kotlin_github.main.users.view.UsersListView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import serg.chuprin.mvp_core.MvpPresenter
import serg.chuprin.mvp_core.annotations.InjectViewState

import javax.inject.Inject


@InjectViewState
class UsersListPresenter
@Inject constructor(private val paginator: Paginator<GithubUserEntity>) : MvpPresenter<UsersListView>() {

    private var paginationDisposable: Disposable? = null

    fun paginate(scrollEvents: Observable<ScrollEvent>) {
        paginationDisposable?.dispose()
        paginationDisposable = paginator.paginate(scrollEvents, { view.paginationProgress(true) })
                .doOnSubscribe { view.showNetworkError(false) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { view.paginationProgress(false) }
                .subscribe({ view.addData(it) }, {
                    it.printStackTrace()
                    view.paginationProgress(false)
                    view.showNetworkError(true)
                })
    }

}