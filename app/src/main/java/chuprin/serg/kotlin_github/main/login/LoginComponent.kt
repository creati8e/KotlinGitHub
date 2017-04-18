package chuprin.serg.kotlin_github.main.login

import chuprin.serg.kotlin_github.app.di.scopes.PerView
import chuprin.serg.kotlin_github.main.login.view.LoginActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(LoginModule::class))
@PerView
interface LoginComponent {

    fun inject(activity: LoginActivity)
}