package chuprin.serg.khub.user.model

import chuprin.serg.khub.common.data.entity.GithubRepositoryEntity
import chuprin.serg.khub.common.data.entity.GithubUserEntity
import chuprin.serg.khub.common.domain.interactor.RepositoryInteractor
import chuprin.serg.khub.common.domain.interactor.UserInteractor
import chuprin.serg.khub.common.presentation.livedata.MutableLiveDataDelegate
import chuprin.serg.khub.common.presentation.viewmodel.BaseViewModel
import com.github.ajalt.timberkt.Timber
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author Sergey Chuprin
 */
class UserInfoViewModel(
    login: String,
    userInteractor: UserInteractor,
    repositoryInteractor: RepositoryInteractor
) : BaseViewModel() {

    val userInfoLiveData = MutableLiveDataDelegate<UserInfoState>()
    val repositoriesLiveData = MutableLiveDataDelegate<List<GithubRepositoryEntity>>()

    init {
        userInteractor
            .getUser(login)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::showUser, Timber::e)
            .untilCleared()

        repositoryInteractor
            .getUserRepositories(login)
            .subscribe(repositoriesLiveData::setter, Timber::e)
            .untilCleared()
    }

    private fun showUser(user: GithubUserEntity) {
        userInfoLiveData.value = UserInfoState(
            user.login,
            user.avatarUrl,
            user.followers.toString(),
            user.following.toString(),
            user.repos.toString()
        )
    }

}