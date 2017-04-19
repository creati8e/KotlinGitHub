package chuprin.serg.kotlin_github.app.domain.interactor.users

import chuprin.serg.kotlin_github.app.data.entity.GithubUserDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.NetworkSpecification
import com.vicpin.krealmextensions.queryFirst
import rx.Observable

class UserLoginSpecification(private val login: String) : DbSpecification<GithubUserDbEntity>,
        NetworkSpecification<GithubUsersApi, GithubUserNetworkEntity> {

    override fun toDbResults(): Observable<GithubUserDbEntity> {
        val cachedUser = GithubUserDbEntity().queryFirst { query -> query.equalTo("login", login) }
        return Observable.just(cachedUser ?: GithubUserDbEntity())
    }

    override fun toNetResults(api: GithubUsersApi) = api.getUser(login)
}