package chuprin.serg.khub.di

import android.content.Context
import chuprin.serg.khub.home.di.HomeComponent
import chuprin.serg.khub.home.di.HomeModule
import chuprin.serg.khub.login.LoginComponent
import chuprin.serg.khub.login.LoginModule
import chuprin.serg.khub.main.repositories.di.RepositoriesListComponent
import chuprin.serg.khub.main.repositories.di.RepositoriesListModule
import chuprin.serg.khub.repositories.RepositoriesActivity
import chuprin.serg.khub.user.di.UserInfoComponent
import chuprin.serg.khub.user.di.UserInfoModule
import chuprin.serg.khub.users.di.UsersComponent
import chuprin.serg.khub.users.di.UsersModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, NetworkModule::class, DomainModule::class])
interface AppComponent {

    fun homeComponent(module: HomeModule): HomeComponent

    fun usersComponent(module: UsersModule): UsersComponent

    fun loginComponent(module: LoginModule): LoginComponent

    fun userComponent(module: UserInfoModule): UserInfoComponent

    fun repositoriesComponent(repositoriesModule: RepositoriesListModule): RepositoriesListComponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): AppComponent.Builder

        fun build(): AppComponent
    }

    fun inject(activity: RepositoriesActivity)

}