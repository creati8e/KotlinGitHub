package chuprin.serg.kotlin_github.user

import chuprin.serg.kotlin_github.app.di.scopes.PerView
import chuprin.serg.kotlin_github.user.view.UserActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(UserModule::class))
@PerView
interface UserComponent {
    fun inject(activity: UserActivity)

}