package chuprin.serg.khub.users.di

import chuprin.serg.khub.KotApplication
import chuprin.serg.khub.di.scope.PerView
import chuprin.serg.khub.users.model.UsersListViewModel
import dagger.Subcomponent

@Subcomponent(modules = [UsersModule::class])
@PerView
interface UsersComponent {

    companion object {
        fun get(): UsersComponent {
            return KotApplication.component.usersComponent(UsersModule())
        }
    }

    val viewModel: UsersListViewModel

}