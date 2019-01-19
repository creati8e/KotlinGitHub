package chuprin.serg.khub.common.data.network.api

import chuprin.serg.khub.common.data.network.dto.GithubRepositoryNetworkDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubRepositoriesApi {

    @GET("repositories")
    fun getRepositories(): Observable<List<GithubRepositoryNetworkDto>>

    @GET("users/{user}/repos")
    fun getUserRepositories(
        @Path("user") user: String
    ): Observable<List<GithubRepositoryNetworkDto>>

}