package chuprin.serg.khub.home.model

import chuprin.serg.khub.common.domain.interactor.UserInteractor
import chuprin.serg.khub.common.presentation.livedata.MutableLiveDataDelegate
import chuprin.serg.khub.common.presentation.viewmodel.BaseViewModel
import com.github.ajalt.timberkt.Timber
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author Sergey Chuprin
 */
class HomeViewModel(
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    val isLoggedId get() = userInteractor.userLoggedIn()
    val userLiveData = MutableLiveDataDelegate<UserState>()

    init {
        userInteractor
            .fetchMe()
            .subscribe({}, {})
            .untilCleared()

        loadUser()
    }

    fun logout() {
        userInteractor.logout()
        loadUser()
    }

    private fun loadUser() {
        userInteractor
            .getMe()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val userState = if (it.id == -1) {
                        UserState("Unauthorized", "", false)
                    } else {
                        UserState(it.login, it.avatarUrl, true)
                    }
                    userLiveData.value = userState
                },
                Timber::e
            )
            .untilCleared()
    }

}