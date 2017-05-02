package chuprin.serg.kotlin_github.app.di

import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity
import chuprin.serg.kotlin_github.app.data.mapper.GithubRepositoryDeserializer
import chuprin.serg.kotlin_github.app.data.network.AuthorizationInterceptor
import chuprin.serg.kotlin_github.app.data.network.GithubApi
import chuprin.serg.kotlin_github.app.data.network.GithubRepositoriesApi
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import chuprin.serg.kotlin_github.app.domain.account.AccountInteractor
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
    fun provideRetrofit(rxAdapter: RxJava2CallAdapterFactory, client: OkHttpClient,
                        gsonConverterFactory: GsonConverterFactory): Retrofit {
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
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                            authInterceptor: AuthorizationInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()
    }

    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(GithubRepositoryNetworkEntity::class.java, GithubRepositoryDeserializer())
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    @Provides
    fun provideAuthInterceptor(interactor: AccountInteractor) = AuthorizationInterceptor(interactor)
}