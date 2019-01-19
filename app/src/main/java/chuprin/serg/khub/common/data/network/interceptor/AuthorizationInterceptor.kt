package chuprin.serg.khub.common.data.network.interceptor

import chuprin.serg.khub.common.domain.interactor.AccountInteractor
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor
@Inject constructor(
    private val accountInteractor: AccountInteractor
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        synchronized(accountInteractor) {
            val token = accountInteractor.getCurrentAccount().token
            if (token.isEmpty()) {
                return chain.proceed(chain.request())
            }
            val request = chain
                .request()
                .newBuilder()
                .addHeader("Authorization", "token $token")
                .build()
            return chain.proceed(request)
        }
    }

}