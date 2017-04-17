package chuprin.serg.kotlin_github.app.data.repository.githubRepository.source

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubRepositoriesApi
import rx.Observable
import javax.inject.Inject

class GithubRepositoriesNetworkSource @Inject constructor(private val api: GithubRepositoriesApi)
    : Source<GithubRepositoryNetworkEntity> {

    override fun put(model: GithubRepositoryNetworkEntity) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun get(key: String): Observable<GithubRepositoryNetworkEntity> {
        throw UnsupportedOperationException("not implemented")
    }

    override fun putAll(models: List<GithubRepositoryNetworkEntity>) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun getAll(): Observable<List<GithubRepositoryNetworkEntity>> = api.getRepositories()
}