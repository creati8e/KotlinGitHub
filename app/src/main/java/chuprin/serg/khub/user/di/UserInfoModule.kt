package chuprin.serg.khub.user.di

import chuprin.serg.khub.common.domain.interactor.RepositoryInteractor
import chuprin.serg.khub.common.domain.interactor.UserInteractor
import chuprin.serg.khub.di.scope.PerView
import chuprin.serg.khub.user.model.UserInfoViewModel
import dagger.Module
import dagger.Provides

@Module
class UserInfoModule(private val login: String) {

    @Provides
    @PerView
    fun provideUserViewModel(
        userInteractor: UserInteractor,
        repositoryInteractor: RepositoryInteractor
    ): UserInfoViewModel {
        return UserInfoViewModel(login, userInteractor, repositoryInteractor)
    }

}
