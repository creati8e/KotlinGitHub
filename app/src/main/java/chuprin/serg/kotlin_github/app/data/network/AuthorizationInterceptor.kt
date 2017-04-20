package chuprin.serg.kotlin_github.app.data.network

import chuprin.serg.kotlin_github.app.data.repository.credentials.CredentialsRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor
@Inject constructor(private val repository: CredentialsRepository) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        val token = repository.getToken()
        if (token.isEmpty()) {
            return chain?.proceed(chain.request())
        }
        val request = chain?.request()
        return chain?.proceed(request?.newBuilder()
                ?.addHeader("Authorization", "token " + token)
                ?.build())
    }
}