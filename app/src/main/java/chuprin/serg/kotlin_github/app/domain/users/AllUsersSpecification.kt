package chuprin.serg.kotlin_github.app.domain.users

import chuprin.serg.kotlin_github.app.data.entity.GithubUserDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.NetworkSpecification
import com.vicpin.krealmextensions.queryAll
import rx.Observable
import rx.schedulers.Schedulers

class AllUsersSpecification : DbSpecification<List<GithubUserDbEntity>>,
        NetworkSpecification<GithubUsersApi, List<GithubUserNetworkEntity>> {

    override fun toDbResults(): Observable<List<GithubUserDbEntity>> {
        return Observable.just(GithubUserDbEntity().queryAll()).subscribeOn(Schedulers.io())
    }

    override fun toNetResults(api: GithubUsersApi) = api.getUsers()

}