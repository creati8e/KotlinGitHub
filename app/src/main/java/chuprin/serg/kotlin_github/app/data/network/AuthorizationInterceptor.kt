package chuprin.serg.kotlin_github.app.data.network

import chuprin.serg.kotlin_github.app.domain.account.AccountInteractor
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor
@Inject constructor(private val accountInteractor: AccountInteractor) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        synchronized(accountInteractor, {
            with(accountInteractor.getCurrentAccount().token) {
                if (isEmpty()) {
                    return chain?.proceed(chain.request())
                }
                return chain?.proceed(chain.request()?.newBuilder()
                        ?.addHeader("Authorization", "token " + this)
                        ?.build())
            }
        })
    }
}