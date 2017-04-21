package chuprin.serg.kotlin_github.app.domain.repositories

import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubRepositoriesApi
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.NetworkSpecification
import com.vicpin.krealmextensions.queryAll
import rx.Observable
import rx.schedulers.Schedulers

class AllRepositoriesSpecification : DbSpecification<List<GithubRepositoryDbEntity>>,
        NetworkSpecification<GithubRepositoriesApi, List<GithubRepositoryNetworkEntity>> {

    override fun toDbResults(): Observable<List<GithubRepositoryDbEntity>> {
        return Observable.just(GithubRepositoryDbEntity().queryAll())
                .subscribeOn(Schedulers.io())
    }

    override fun toNetResults(api: GithubRepositoriesApi) = api.getRepositories()
}