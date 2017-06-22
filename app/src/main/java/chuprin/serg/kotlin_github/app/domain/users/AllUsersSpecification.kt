package chuprin.serg.kotlin_github.app.domain.users

import chuprin.serg.kotlin_github.app.data.entity.GithubUserDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.NetworkSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.PaginationSpecification
import com.vicpin.krealmextensions.queryAll
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class AllUsersSpecification : DbSpecification<List<GithubUserDbEntity>>,
        NetworkSpecification<GithubUsersApi, List<GithubUserNetworkEntity>>,
        PaginationSpecification {

    override var offset: Int = 0

    override fun toDbResults(): Observable<List<GithubUserDbEntity>> {
        return Observable.just(GithubUserDbEntity().queryAll()).subscribeOn(Schedulers.io())
    }

    override fun toNetResults(api: GithubUsersApi): Observable<List<GithubUserNetworkEntity>> {
        return api.getUsers(if (offset > 0) offset else null)
    }

}