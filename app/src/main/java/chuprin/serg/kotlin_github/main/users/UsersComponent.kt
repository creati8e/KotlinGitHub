package chuprin.serg.kotlin_github.main.users

import chuprin.serg.kotlin_github.app.di.scopes.PerView
import chuprin.serg.kotlin_github.main.users.view.UsersListFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(UsersModule::class))
@PerView
interface UsersComponent {

    fun inject(fragment: UsersListFragment)

}