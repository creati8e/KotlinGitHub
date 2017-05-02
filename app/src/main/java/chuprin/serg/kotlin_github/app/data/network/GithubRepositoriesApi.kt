package chuprin.serg.kotlin_github.app.data.network

import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubRepositoriesApi {

    @GET("repositories")
    fun getRepositories(): Observable<List<GithubRepositoryNetworkEntity>>

    @GET("users/{user}/repos")
    fun getUserRepositories(@Path("user") user: String): Observable<List<GithubRepositoryNetworkEntity>>
}