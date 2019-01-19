package chuprin.serg.khub.common.data.network.api

import chuprin.serg.khub.login.model.entity.AuthResponse
import io.reactivex.Single
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

interface GithubAuthApi {

    @POST
    @Headers("Accept: application/json")
    fun exchangeToken(@Url url: String): Single<AuthResponse>

}