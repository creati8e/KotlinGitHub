package chuprin.serg.kotlin_github.user;

import chuprin.serg.kotlin_github.app.di.scopes.PerView
import chuprin.serg.kotlin_github.app.domain.interactor.UsersInteractor
import chuprin.serg.kotlin_github.user.presenter.UserPresenter
import dagger.Module
import dagger.Provides

@Module class UserModule {

    @Provides
    @PerView
    fun providePresenter(usersInteractor: UsersInteractor): UserPresenter {
        return UserPresenter(usersInteractor)
    }
}
