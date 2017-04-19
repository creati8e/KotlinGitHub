package chuprin.serg.kotlin_github.main.login

import android.os.Bundle
import chuprin.serg.kotlin_github.app.data.network.GithubAuthApi
import chuprin.serg.kotlin_github.app.data.repository.credentials.CredentialsRepository
import chuprin.serg.kotlin_github.app.data.repository.githubUser.GithubUsersRepository
import chuprin.serg.kotlin_github.main.login.model.LoginInteractor
import chuprin.serg.mvpcore.cache.PresenterModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginModule(bundle: Bundle?) : PresenterModule(bundle) {

    @Provides
    fun provideInteractor(repository: CredentialsRepository,
                          usersRepository: GithubUsersRepository,
                          api: GithubAuthApi): LoginInteractor {
        return LoginInteractor(repository, usersRepository, api)
    }

    @Provides
    fun provideAuthApi(retrofit: Retrofit): GithubAuthApi = retrofit.create(GithubAuthApi::class.java)
}