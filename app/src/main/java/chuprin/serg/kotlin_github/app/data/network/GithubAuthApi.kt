package chuprin.serg.kotlin_github.app.data.network

import chuprin.serg.kotlin_github.main.login.model.entity.AuthResponse
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url
import rx.Single


interface GithubAuthApi {

    @POST
    @Headers("Accept: application/json")
    fun exchangeToken(@Url url: String): Single<AuthResponse>

}