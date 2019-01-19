package chuprin.serg.khub.login

import chuprin.serg.khub.common.data.network.api.GithubAuthApi
import chuprin.serg.khub.common.data.repository.CredentialsRepository
import chuprin.serg.khub.common.data.repository.GithubUserRepository
import chuprin.serg.khub.di.scope.PerView
import chuprin.serg.khub.login.model.LoginInteractor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginModule {

    @PerView
    @Provides
    fun provideInteractor(
        api: GithubAuthApi,
        repository: CredentialsRepository,
        userRepository: GithubUserRepository
    ): LoginInteractor {
        return LoginInteractor(api, userRepository, repository)
    }

    @PerView
    @Provides
    fun provideAuthApi(retrofit: Retrofit): GithubAuthApi {
        return retrofit.create(GithubAuthApi::class.java)
    }

}