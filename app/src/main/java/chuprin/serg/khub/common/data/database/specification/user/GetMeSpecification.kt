package chuprin.serg.khub.common.data.database.specification.user

import chuprin.serg.khub.common.data.network.api.GithubUsersApi
import chuprin.serg.khub.common.data.network.dto.GithubUserNetworkDto
import io.reactivex.Observable

class GetMeSpecification(login: String = "") : UserLoginSpecification(login) {

    override fun toNetResults(api: GithubUsersApi): Observable<GithubUserNetworkDto> {
        return api.getMe().doOnNext { login = it.login }
    }

}