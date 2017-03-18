package chuprin.serg.kotlin_github.user;

import android.os.Bundle
import chuprin.serg.kotlin_github.app.di.scopes.PerView
import chuprin.serg.kotlin_github.app.domain.interactor.UsersInteractor
import chuprin.serg.kotlin_github.app.mvp.cache.PresenterModule
import chuprin.serg.kotlin_github.user.presenter.UserPresenter
import dagger.Module
import dagger.Provides

@Module class UserModule(bundle: Bundle?) : PresenterModule(bundle) {

    @Provides
    @PerView
    fun providePresenter(usersInteractor: UsersInteractor): UserPresenter {
        if (bundle == null) {
            return UserPresenter(usersInteractor)
        }
        return cache.get(bundle) as UserPresenter
    }
}
