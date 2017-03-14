package chuprin.serg.kotlin_github.app.data.repository.user.source

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.UserNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import rx.Observable
import javax.inject.Inject

class UserNetworkSource @Inject constructor(val api: GithubUsersApi) : Source<UserNetworkEntity> {

    override fun getAll(): Observable<List<UserNetworkEntity>> = api.getUsers()

    override fun get(key: String): Observable<UserNetworkEntity> = api.getUser(key)

    override fun put(model: UserNetworkEntity) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun putAll(models: List<UserNetworkEntity>) {
        throw UnsupportedOperationException("not implemented")
    }
}