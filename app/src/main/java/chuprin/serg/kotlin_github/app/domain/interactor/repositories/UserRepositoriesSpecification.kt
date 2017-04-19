package chuprin.serg.kotlin_github.app.domain.interactor.repositories

import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubRepositoriesApi
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.NetworkSpecification
import com.vicpin.krealmextensions.query
import rx.Observable
import rx.schedulers.Schedulers

class UserRepositoriesSpecification(private val userLogin: String) : DbSpecification<List<GithubRepositoryDbEntity>>,
        NetworkSpecification<GithubRepositoriesApi, List<GithubRepositoryNetworkEntity>> {

    override fun toDbResults(): Observable<List<GithubRepositoryDbEntity>> {
        return Observable.just(GithubRepositoryDbEntity()
                .query { it.equalTo("ownerName", userLogin) })
                .subscribeOn(Schedulers.io())
    }

    override fun toNetResults(api: GithubRepositoriesApi) = api.getUserRepositories(userLogin)
}