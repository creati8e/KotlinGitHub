package chuprin.serg.kotlin_github.app.di

import android.content.Context
import chuprin.serg.kotlin_github.main.MainComponent
import chuprin.serg.kotlin_github.main.MainModule
import chuprin.serg.kotlin_github.main.login.LoginComponent
import chuprin.serg.kotlin_github.main.login.LoginModule
import chuprin.serg.kotlin_github.user.UserComponent
import chuprin.serg.kotlin_github.user.UserModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(DataModule::class, NetworkModule::class))
@Singleton
interface AppComponent {

    fun mainComponent(module: MainModule): MainComponent

    fun userComponent(module: UserModule): UserComponent

    fun loginComponent(module: LoginModule): LoginComponent

    @Component.Builder
    interface Builder {
        @BindsInstance fun context(context: Context): AppComponent.Builder
        fun build(): AppComponent
    }
}