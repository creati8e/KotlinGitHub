package chuprin.serg.kotlin_github.app.data.repository.githubUser.source

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import chuprin.serg.kotlin_github.app.data.repository.specification.NetworkSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import rx.Observable
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class GithubUsersNetworkSource @Inject constructor(val api: GithubUsersApi) : Source<GithubUserNetworkEntity> {

    override fun put(model: GithubUserNetworkEntity) = Unit

    override fun putAll(models: List<GithubUserNetworkEntity>) = Unit

    override fun get(specification: Specification): Observable<GithubUserNetworkEntity> {
        return specification<GithubUserNetworkEntity>(specification).toNetResults(api)
    }

    override fun delete(model: GithubUserNetworkEntity) = Unit

    override fun getList(specification: Specification): Observable<List<GithubUserNetworkEntity>> {
        return specification<List<GithubUserNetworkEntity>>(specification).toNetResults(api)
    }

    private fun <MODEL> specification(specification: Specification): NetworkSpecification<GithubUsersApi, MODEL> {
        return specification as NetworkSpecification<GithubUsersApi, MODEL>
    }
}