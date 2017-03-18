package chuprin.serg.kotlin_github.main

import chuprin.serg.kotlin_github.app.di.AppComponent
import chuprin.serg.kotlin_github.app.di.scopes.PerView
import chuprin.serg.kotlin_github.main.repos.view.ReposFragment
import chuprin.serg.kotlin_github.main.users.view.UserListFragment
import dagger.Component

@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainModule::class))
@PerView
interface MainComponent {
    fun inject(fragment: UserListFragment)

    fun inject(fragment: ReposFragment)
}