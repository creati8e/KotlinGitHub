package chuprin.serg.kotlin_github.main

import android.os.Bundle
import chuprin.serg.kotlin_github.app.domain.interactor.repositories.RepositoriesInteractor
import chuprin.serg.kotlin_github.app.domain.interactor.users.UsersInteractor
import chuprin.serg.kotlin_github.main.repositories.presenter.RepositoriesListPresenter
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
        return getPresenter({ RepositoriesListPresenter(interactor) }, RepositoriesListPresenter::class.java)
    }
}