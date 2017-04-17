package chuprin.serg.kotlin_github.app.di

import chuprin.serg.kotlin_github.main.MainComponent
import chuprin.serg.kotlin_github.main.MainModule
import chuprin.serg.kotlin_github.user.UserComponent
import chuprin.serg.kotlin_github.user.UserModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(DataModule::class))
@Singleton
interface AppComponent {

    fun mainComponent(module: MainModule): MainComponent

    fun userComponent(module: UserModule): UserComponent
}