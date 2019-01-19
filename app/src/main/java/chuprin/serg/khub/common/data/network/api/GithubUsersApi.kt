package chuprin.serg.khub.common.data.network.api

import chuprin.serg.khub.common.data.network.dto.GithubUserNetworkDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUsersApi {

    @GET("users?per_page=10")
    fun getUsers(@Query("since") offset: Int?): Observable<List<GithubUserNetworkDto>>

    @GET("users/{username}")
    fun getUser(@Path("username") userId: String): Observable<GithubUserNetworkDto>

    @GET("user")
    fun getMe(): Observable<GithubUserNetworkDto>

}