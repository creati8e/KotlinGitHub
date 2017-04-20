package chuprin.serg.kotlin_github.app.domain.users

import chuprin.serg.kotlin_github.app.data.entity.GithubUserDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.NetworkSpecification
import com.vicpin.krealmextensions.queryFirst
import rx.Observable

class GetMeSpecification(var userId: Int) : NetworkSpecification<GithubUsersApi, GithubUserNetworkEntity>,
        DbSpecification<GithubUserDbEntity> {

    override fun toDbResults(): Observable<GithubUserDbEntity> {
        val cachedUser = GithubUserDbEntity().queryFirst { query -> query.equalTo("id", userId) }
        return Observable.just(cachedUser ?: GithubUserDbEntity())
    }

    override fun toNetResults(api: GithubUsersApi): Observable<GithubUserNetworkEntity> {
        return api.getMe().doOnNext { userId = it.id }
    }
}