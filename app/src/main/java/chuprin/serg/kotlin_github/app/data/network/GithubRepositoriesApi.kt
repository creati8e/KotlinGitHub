package chuprin.serg.kotlin_github.app.data.network

import chuprin.serg.kotlin_github.app.data.entity.RepoNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable


interface GithubRepositoriesApi {

    @GET("repositories")
    fun getRepositories(): Observable<List<RepoNetworkEntity>>

    @GET("users/{user}/repos")
    fun getUserRepositories(@Path("user") user: String): Observable<List<RepoNetworkEntity>>
}