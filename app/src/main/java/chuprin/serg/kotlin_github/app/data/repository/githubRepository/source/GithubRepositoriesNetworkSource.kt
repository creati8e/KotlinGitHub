package chuprin.serg.kotlin_github.app.data.repository.githubRepository.source

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubRepositoriesApi
import chuprin.serg.kotlin_github.app.data.repository.specification.NetworkSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import rx.Observable
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class GithubRepositoriesNetworkSource @Inject constructor(private val api: GithubRepositoriesApi)
    : Source<GithubRepositoryNetworkEntity> {

    override fun putAll(models: List<GithubRepositoryNetworkEntity>) = Unit

    override fun put(model: GithubRepositoryNetworkEntity) = Unit

    override fun get(specification: Specification): Observable<GithubRepositoryNetworkEntity> {
        return specification<GithubRepositoryNetworkEntity>(specification).toNetResults(api)
    }

    override fun getList(specification: Specification): Observable<List<GithubRepositoryNetworkEntity>> {
        return specification<List<GithubRepositoryNetworkEntity>>(specification).toNetResults(api)
    }

    private fun <MODEL> specification(specification: Specification): NetworkSpecification<GithubRepositoriesApi, MODEL> {
        return specification as NetworkSpecification<GithubRepositoriesApi, MODEL>
    }
}