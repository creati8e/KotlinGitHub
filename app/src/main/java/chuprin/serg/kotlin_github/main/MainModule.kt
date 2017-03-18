package chuprin.serg.kotlin_github.main

import android.os.Bundle
import chuprin.serg.kotlin_github.app.domain.interactor.ReposInteractor
import chuprin.serg.kotlin_github.app.domain.interactor.UsersInteractor
import chuprin.serg.kotlin_github.app.mvp.cache.PresenterModule
import chuprin.serg.kotlin_github.main.repos.presenter.ReposPresenter
import chuprin.serg.kotlin_github.main.users.presenter.UserListPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule(bundle: Bundle?) : PresenterModule(bundle) {

    @Provides
    fun provideUsersPresenter(interactor: UsersInteractor): UserListPresenter {
        if (bundle == null) {
            return UserListPresenter(interactor)
        }
        return cache.get(bundle) as UserListPresenter
    }

    @Provides
    fun provideReposPresenter(interactor: ReposInteractor): ReposPresenter {
        if (bundle == null) {
            return ReposPresenter(interactor)
        }
        return cache.get(bundle) as ReposPresenter
    }
}