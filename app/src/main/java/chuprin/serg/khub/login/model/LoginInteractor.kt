package chuprin.serg.khub.login.model

import android.content.Intent
import android.net.Uri
import chuprin.serg.khub.common.data.database.dto.GithubAccountDbDto
import chuprin.serg.khub.common.data.database.specification.user.GetMeSpecification
import chuprin.serg.khub.common.data.network.api.GithubAuthApi
import chuprin.serg.khub.common.data.repository.CachePolicy
import chuprin.serg.khub.common.data.repository.CredentialsRepository
import chuprin.serg.khub.common.data.repository.GithubUserRepository
import chuprin.serg.khub.login.model.entity.NoAuthError
import io.reactivex.Completable

class LoginInteractor(
    private val api: GithubAuthApi,
    private val userRepository: GithubUserRepository,
    private val credentialsRepository: CredentialsRepository
) {

    private companion object {
        private const val TOKEN_URL: String = "https://github.com/login/oauth/access_token"
        private const val AUTH_URL: String = "https://github.com/login/oauth/authorize"
        private const val PARAM_CLIENT_ID = "client_id"
        private const val PARAM_CLIENT_SECRET = "client_secret"
        private const val PARAM_CODE = "code"
        private const val PARAM_SCOPE = "scope"
        private const val PARAM_CALLBACK_URI = "redirect_uri"
        private const val CLIENT_ID = "7ce5524f1e36302de3da"
        private const val CLIENT_SECRET = "23af43e54246e72997ec700ef7b21d6fe8615726"
        private val CALLBACK_URI = Uri.parse("kothub://auth")
        private const val SCOPES = "user,repo,gist"

    }

    fun buildUrl(): Uri {
        return Uri.parse(AUTH_URL)
            .buildUpon()
            .appendQueryParameter(PARAM_CLIENT_ID, CLIENT_ID)
            .appendQueryParameter(PARAM_SCOPE, SCOPES)
            .appendQueryParameter(PARAM_CALLBACK_URI, CALLBACK_URI.toString())
            .build()
    }

    fun retrieveToken(intent: Intent?): Completable {
        val data = intent?.data
        if (data == null || data.scheme != CALLBACK_URI.scheme || data.host != CALLBACK_URI.host) {
            return Completable.error(NoAuthError())
        }
        val uri = Uri
            .parse(TOKEN_URL)
            .buildUpon()
            .appendQueryParameter(PARAM_CLIENT_ID, CLIENT_ID)
            .appendQueryParameter(PARAM_CLIENT_SECRET, CLIENT_SECRET)
            .appendQueryParameter(PARAM_CODE, data.getQueryParameter(PARAM_CODE))
            .build()

        return api
            .exchangeToken(uri.toString())
            .map {
                GithubAccountDbDto()
                    .apply { token = it.accessToken; active = true }
            }
            .doOnSuccess { credentialsRepository.put(it) }
            .flatMapObservable { account ->
                userRepository.get(GetMeSpecification(), CachePolicy.NETWORK)
                    .map { account.apply { id = it.id; login = it.login } }
            }.doOnNext { credentialsRepository.put(it) }
            .ignoreElements()
    }
}