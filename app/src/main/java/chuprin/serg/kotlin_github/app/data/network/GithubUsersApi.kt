package chuprin.serg.kotlin_github.app.data.network

import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface GithubUsersApi {
    @GET("users")
    fun getUsers(): Observable<List<GithubUserNetworkEntity>>

    @GET("users/{username}")
    fun getUser(@Path("username") userId: String): Observable<GithubUserNetworkEntity>

    @GET("user")
    fun getMe(): Observable<GithubUserNetworkEntity>
}