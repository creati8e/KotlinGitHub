package chuprin.serg.kotlin_github.app.domain.users

import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import rx.Observable

class GetMeSpecification(login: String = "") : UserLoginSpecification(login) {

    override fun toNetResults(api: GithubUsersApi): Observable<GithubUserNetworkEntity> {
        return api.getMe().doOnNext { login = it.login }
    }
}