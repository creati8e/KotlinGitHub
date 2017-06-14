package chuprin.serg.kotlin_github.main

import android.os.Bundle
import chuprin.serg.kotlin_github.app.di.scopes.PerView
import chuprin.serg.kotlin_github.app.domain.repositories.RepositoriesInteractor
import chuprin.serg.kotlin_github.app.domain.users.UsersInteractor
import chuprin.serg.kotlin_github.main.presenter.MainPresenter
import chuprin.serg.kotlin_github.main.repositories.presenter.RepositoriesListPresenter
import chuprin.serg.kotlin_github.main.repositories.view.RepositoriesListFragment
import chuprin.serg.kotlin_github.main.users.presenter.UsersListPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule(val bundle: Bundle?) {

    @Provides
    @PerView
    fun provideUsersPresenter(interactor: UsersInteractor): UsersListPresenter {
        return UsersListPresenter(interactor)
    }

    @Provides
    @PerView
    fun provideReposPresenter(interactor: RepositoriesInteractor): RepositoriesListPresenter {
        val userLogin = bundle?.getString(RepositoriesListFragment.BUNDLE_USER_LOGIN, "") ?: ""
        return RepositoriesListPresenter(interactor, userLogin)
    }

    @Provides
    @PerView
    fun provideMainPresenter(interactor: UsersInteractor): MainPresenter {
        return MainPresenter(interactor)
    }
}