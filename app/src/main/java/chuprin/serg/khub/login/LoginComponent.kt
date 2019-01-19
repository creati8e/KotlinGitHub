package chuprin.serg.khub.login

import chuprin.serg.khub.di.scope.PerView
import chuprin.serg.khub.login.view.LoginActivity
import dagger.Subcomponent

@PerView
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

    fun inject(activity: LoginActivity)

}