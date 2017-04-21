package chuprin.serg.kotlin_github.main

import android.os.Bundle
import chuprin.serg.kotlin_github.app.domain.repositories.RepositoriesInteractor
import chuprin.serg.kotlin_github.app.domain.users.UsersInteractor
import chuprin.serg.kotlin_github.main.presenter.MainPresenter
import chuprin.serg.kotlin_github.main.repositories.presenter.RepositoriesListPresenter
import chuprin.serg.kotlin_github.main.repositories.view.RepositoriesListFragment
import chuprin.serg.kotlin_github.main.users.presenter.UsersListPresenter
import chuprin.serg.mvpcore.cache.PresenterModule
import dagger.Module
import dagger.Provides

@Module
class MainModule(bundle: Bundle?) : PresenterModule(bundle) {

    @Provides
    fun provideUsersPresenter(interactor: UsersInteractor): UsersListPresenter {
        return getPresenter({ UsersListPresenter(interactor) }, UsersListPresenter::class.java)
    }

    @Provides
    fun provideReposPresenter(interactor: RepositoriesInteractor): RepositoriesListPresenter {
        val userLogin = bundle.getString(RepositoriesListFragment.BUNDLE_USER_LOGIN, "")
        return getPresenter({ RepositoriesListPresenter(interactor, userLogin) }, RepositoriesListPresenter::class.java)
    }

    @Provides
    fun provideMainPresenter(interactor: UsersInteractor): MainPresenter {
        return getPresenter({ MainPresenter(interactor) }, MainPresenter::class.java)
    }
}