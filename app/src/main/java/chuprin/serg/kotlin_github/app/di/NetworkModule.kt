package chuprin.serg.kotlin_github.app.di

import chuprin.serg.kotlin_github.app.data.network.GithubRepositoriesApi
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers


@Module
class NetworkModule {

    @Provides
    fun provideGithubReposApi(retrofit: Retrofit): GithubRepositoriesApi {
        return retrofit.create<GithubRepositoriesApi>(GithubRepositoriesApi::class.java)
    }

    @Provides
    fun provideGithubUsersApi(retrofit: Retrofit): GithubUsersApi {
        return retrofit.create<GithubUsersApi>(GithubUsersApi::class.java)
    }

    @Provides
    fun provideRetrofit(rxAdapter: RxJavaCallAdapterFactory, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build()
    }

    @Provides
    fun provideRxAdapterFactory(): RxJavaCallAdapterFactory {
        return RxJavaCallAdapterFactory.createWithScheduler(Schedulers.newThread())
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

}