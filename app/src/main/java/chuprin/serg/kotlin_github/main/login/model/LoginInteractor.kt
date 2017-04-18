package chuprin.serg.kotlin_github.main.login.model

import android.content.Intent
import android.net.Uri
import chuprin.serg.kotlin_github.app.data.network.GithubAuthApi
import chuprin.serg.kotlin_github.app.data.repository.credentials.CredentialsRepository
import chuprin.serg.kotlin_github.main.login.model.entity.NoAuthError
import rx.Completable
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val repository: CredentialsRepository,
                                          private val api: GithubAuthApi) {

    private val TOKEN_URL: String = "https://github.com/login/oauth/access_token"
    private val AUTH_URL: String = "https://github.com/login/oauth/authorize"
    private val PARAM_CLIENT_ID = "client_id"
    private val PARAM_CLIENT_SECRET = "client_secret"
    private val PARAM_CODE = "code"
    private val PARAM_SCOPE = "scope"
    private val PARAM_CALLBACK_URI = "redirect_uri"
    private val CLIENT_ID = "7ce5524f1e36302de3da"
    private val CLIENT_SECRET = "23af43e54246e72997ec700ef7b21d6fe8615726"
    private val CALLBACK_URI = Uri.parse("kothub://auth")
    private val SCOPES = "user,repo,gist"

    fun buildUrl(): Uri = Uri.parse(AUTH_URL)
            .buildUpon()
            .appendQueryParameter(PARAM_CLIENT_ID, CLIENT_ID)
            .appendQueryParameter(PARAM_SCOPE, SCOPES)
            .appendQueryParameter(PARAM_CALLBACK_URI, CALLBACK_URI.toString())
            .build()

    fun retrieveToken(intent: Intent?): Completable {
        val data = intent?.data
        if (data == null || data.scheme != CALLBACK_URI.scheme || data.host != CALLBACK_URI.host) {
            return Completable.error(NoAuthError())
        }
        return api.exchangeToken(Uri.parse(TOKEN_URL)
                .buildUpon()
                .appendQueryParameter(PARAM_CLIENT_ID, CLIENT_ID)
                .appendQueryParameter(PARAM_CLIENT_SECRET, CLIENT_SECRET)
                .appendQueryParameter(PARAM_CODE, data.getQueryParameter(PARAM_CODE))
                .build()
                .toString())
                .doOnSuccess { repository.put(it.accessToken) }
                .toCompletable()
    }
}