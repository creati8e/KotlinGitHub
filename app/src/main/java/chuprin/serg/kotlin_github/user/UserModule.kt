package chuprin.serg.kotlin_github.user;

import android.os.Bundle
import chuprin.serg.kotlin_github.app.di.scopes.PerView
import chuprin.serg.kotlin_github.app.domain.interactor.UsersInteractor
import chuprin.serg.kotlin_github.user.presenter.UserPresenter
import chuprin.serg.mvpcore.cache.PresenterModule
import dagger.Module
import dagger.Provides

@Module class UserModule(bundle: Bundle?) : PresenterModule(bundle) {

    @Provides
    @PerView
    fun providePresenter(interactor: UsersInteractor): UserPresenter {
        return getPresenter({ UserPresenter(interactor, bundle.getString("login")) },
                UserPresenter::class.java)
    }
}
