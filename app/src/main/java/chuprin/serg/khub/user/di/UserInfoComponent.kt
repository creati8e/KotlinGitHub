package chuprin.serg.khub.user.di

import chuprin.serg.khub.KotApplication
import chuprin.serg.khub.di.scope.PerView
import chuprin.serg.khub.user.model.UserInfoViewModel
import dagger.Subcomponent

@Subcomponent(modules = [UserInfoModule::class])
@PerView
interface UserInfoComponent {

    companion object {
        fun get(login: String): UserInfoComponent {
            return KotApplication.component.userComponent(UserInfoModule(login))
        }
    }

    val viewModel: UserInfoViewModel

}