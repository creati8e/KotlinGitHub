package chuprin.serg.kotlin_github.main

import chuprin.serg.kotlin_github.app.di.scopes.PerView
import chuprin.serg.kotlin_github.main.repositories.view.RepositoriesListFragment
import chuprin.serg.kotlin_github.main.users.view.UsersListFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainModule::class))
@PerView
interface MainComponent {
    fun inject(fragment: UsersListFragment)

    fun inject(fragment: RepositoriesListFragment)
}