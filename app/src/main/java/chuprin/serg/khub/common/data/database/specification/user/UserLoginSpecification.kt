package chuprin.serg.khub.common.data.database.specification.user

import chuprin.serg.khub.common.data.database.dto.GithubUserDbDto
import chuprin.serg.khub.common.data.network.api.GithubUsersApi
import chuprin.serg.khub.common.data.network.dto.GithubUserNetworkDto
import chuprin.serg.khub.common.data.repository.specification.DbSpecification
import chuprin.serg.khub.common.data.repository.specification.NetworkSpecification
import com.vicpin.krealmextensions.queryFirst
import io.reactivex.Observable

open class UserLoginSpecification(
    var login: String
) : DbSpecification<GithubUserDbDto>,
    NetworkSpecification<GithubUsersApi, GithubUserNetworkDto> {

    override fun toDbResults(): Observable<GithubUserDbDto> {
        val cachedUser = GithubUserDbDto()
            .queryFirst { query -> query.equalTo("login", login) }
        return Observable.just(cachedUser ?: GithubUserDbDto())
    }

    override fun toNetResults(api: GithubUsersApi) = api.getUser(login)

}