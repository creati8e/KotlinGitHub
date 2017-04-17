package chuprin.serg.kotlin_github.app.data.repository.githubUser.source

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import rx.Observable
import javax.inject.Inject

class GithubUsersNetworkSource @Inject constructor(val api: GithubUsersApi) : Source<GithubUserNetworkEntity> {

    override fun getAll(): Observable<List<GithubUserNetworkEntity>> = api.getUsers()

    override fun get(key: String): Observable<GithubUserNetworkEntity> = api.getUser(key)

    override fun put(model: GithubUserNetworkEntity) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun putAll(models: List<GithubUserNetworkEntity>) {
        throw UnsupportedOperationException("not implemented")
    }
}