package chuprin.serg.kotlin_github.main

import chuprin.serg.kotlin_github.app.di.scopes.PerView
import chuprin.serg.kotlin_github.app.domain.interactor.ReposInteractor
import chuprin.serg.kotlin_github.app.domain.interactor.UsersInteractor
import chuprin.serg.kotlin_github.main.repos.presenter.ReposPresenter
import chuprin.serg.kotlin_github.main.users.presenter.UserListPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @PerView
    fun provideUsersPresenter(interactor: UsersInteractor): UserListPresenter = UserListPresenter(interactor)

    @Provides
    @PerView
    fun provideReposPresenter(interactor: ReposInteractor): ReposPresenter = ReposPresenter(interactor)
}