package chuprin.serg.kotlin_github.app.data.repository.repo.source

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.RepoNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubRepositoriesApi
import rx.Observable
import javax.inject.Inject

class RepoNetworkSource @Inject constructor(private val api: GithubRepositoriesApi) : Source<RepoNetworkEntity> {
    override fun put(model: RepoNetworkEntity) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun get(key: String): Observable<RepoNetworkEntity> {
        throw UnsupportedOperationException("not implemented")
    }

    override fun putAll(models: List<RepoNetworkEntity>) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun getAll(): Observable<List<RepoNetworkEntity>> = api.getRepositories()
}