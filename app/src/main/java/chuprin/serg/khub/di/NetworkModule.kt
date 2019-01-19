package chuprin.serg.khub.di

import chuprin.serg.khub.common.data.network.api.GithubApi
import chuprin.serg.khub.common.data.network.api.GithubRepositoriesApi
import chuprin.serg.khub.common.data.network.api.GithubUsersApi
import chuprin.serg.khub.common.data.network.dto.GithubRepositoryNetworkDto
import chuprin.serg.khub.common.data.network.interceptor.AuthorizationInterceptor
import chuprin.serg.khub.common.data.network.serialization.GithubRepositoryDeserializer
import chuprin.serg.khub.common.domain.interactor.AccountInteractor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    @Provides
    fun provideGithubReposApi(retrofit: Retrofit): GithubRepositoriesApi {
        return retrofit.create(GithubRepositoriesApi::class.java)
    }

    @Provides
    fun provideGithubUsersApi(retrofit: Retrofit): GithubUsersApi {
        return retrofit.create(GithubUsersApi::class.java)
    }

    @Provides
    fun provideRetrofit(
        rxAdapter: RxJava2CallAdapterFactory, client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(GithubApi.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxAdapter)
            .build()
    }

    @Provides
    fun provideRxAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.newThread())
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthorizationInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(
            GithubRepositoryNetworkDto::class.java,
            GithubRepositoryDeserializer()
        )
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    @Provides
    fun provideAuthInterceptor(interactor: AccountInteractor) =
        AuthorizationInterceptor(interactor)

}