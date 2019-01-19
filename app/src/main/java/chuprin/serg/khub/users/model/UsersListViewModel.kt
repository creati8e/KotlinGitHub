package chuprin.serg.khub.users.model

import chuprin.serg.khub.common.data.entity.GithubUserEntity
import chuprin.serg.khub.common.domain.pagintation.Paginator
import chuprin.serg.khub.common.domain.pagintation.ScrollEvent
import chuprin.serg.khub.common.presentation.livedata.MutableLiveDataDelegate
import chuprin.serg.khub.common.presentation.viewmodel.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

/**
 * @author Sergey Chuprin
 */
class UsersListViewModel(
    private val paginator: Paginator<GithubUserEntity>
) : BaseViewModel() {

    val listLiveData = MutableLiveDataDelegate<List<GithubUserEntity>>()

    private var paginationDisposable: Disposable? = null

    fun paginate(scrollEvents: Observable<ScrollEvent>) {
        paginationDisposable?.dispose()
        paginationDisposable = paginator
            .paginate(scrollEvents) {
                // TODO
                // view.paginationProgress(true)
            }
            .doOnSubscribe {
                // TODO
//                view.showNetworkError(false)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                // TODO
//                view.paginationProgress(false)
            }
            .subscribe({
                listLiveData.value = it
                //                view.addData(it)
            }, {
                it.printStackTrace()

                // TODO
//                view.paginationProgress(false)
//                view.showNetworkError(true)
            })
    }


}