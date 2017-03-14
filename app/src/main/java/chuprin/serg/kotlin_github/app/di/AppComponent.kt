package chuprin.serg.kotlin_github.app.di

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.entity.RepoEntity
import chuprin.serg.kotlin_github.app.data.entity.UserEntity
import chuprin.serg.kotlin_github.main.MainComponent
import chuprin.serg.kotlin_github.user.UserComponent
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(DataModule::class))
@Singleton
interface AppComponent {

    fun getUserRepo(): AbsRepository<UserEntity>

    fun getRepositoryRepo(): AbsRepository<RepoEntity>

    fun getMainComponent(): MainComponent

    fun getUserComponent(): UserComponent


}