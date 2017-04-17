package chuprin.serg.kotlin_github.app.data.repository.githubUser

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubUserDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity
import chuprin.serg.kotlin_github.app.data.mapper.mapDbListToEntity
import chuprin.serg.kotlin_github.app.data.mapper.mapDbToEntity
import chuprin.serg.kotlin_github.app.data.mapper.mapNetListToDb
import chuprin.serg.kotlin_github.app.data.mapper.mapNetToDb
import rx.Observable
import javax.inject.Inject

class GithubUsersRepository @Inject constructor(
        val dbSource: Source<GithubUserDbEntity>,
        val netSource: Source<GithubUserNetworkEntity>) : AbsRepository<GithubUserEntity> {

    override fun getAll(): Observable<List<GithubUserEntity>> {
        val dbUsers = dbSource.getAll().map(List<GithubUserDbEntity>::mapDbListToEntity)

        val netUsers = netSource.getAll()
                .doOnNext { dbSource.putAll(it.mapNetListToDb()) }
                .flatMap { dbSource.getAll() }
                .onErrorResumeNext(dbSource.getAll())
                .map(List<GithubUserDbEntity>::mapDbListToEntity)

        return Observable.concat(dbUsers, netUsers)
    }

    override fun get(key: String): Observable<GithubUserEntity> {
        val dbUser = dbSource.get(key).map(GithubUserDbEntity::mapDbToEntity)

        val netUser = netSource.get(key)
                .doOnNext { dbSource.put(it.mapNetToDb()) }
                .flatMap { dbSource.get(key) }
                .onErrorResumeNext(dbSource.get(key))
                .map(GithubUserDbEntity::mapDbToEntity)

        return Observable.concat(dbUser, netUser)
    }
}