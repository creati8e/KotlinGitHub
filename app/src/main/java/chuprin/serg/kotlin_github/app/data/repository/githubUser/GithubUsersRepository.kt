package chuprin.serg.kotlin_github.app.data.repository.githubUser

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubUserDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity
import chuprin.serg.kotlin_github.app.data.mapper.*
import chuprin.serg.kotlin_github.app.data.repository.CachePolicy
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import rx.Observable
import javax.inject.Inject

class GithubUsersRepository @Inject constructor(
        private val dbSource: Source<GithubUserDbEntity>,
        private val netSource: Source<GithubUserNetworkEntity>) : AbsRepository<GithubUserEntity> {

    override fun put(model: GithubUserEntity) = dbSource.put(model.mapEntityToDb())

    override fun get(specification: Specification, cachePolicy: CachePolicy): Observable<GithubUserEntity> {
        when (cachePolicy) {
            is CachePolicy.BOTH -> return Observable.concat(getDbUser(specification), getNetUser(specification))
            is CachePolicy.CACHE_ONLY -> return getDbUser(specification)
            is CachePolicy.NET_ONLY -> return getNetUser(specification)
        }
    }

    override fun getList(specification: Specification): Observable<List<GithubUserEntity>> {
        val dbUsers = dbSource.getList(specification).map(List<GithubUserDbEntity>::mapDbListToEntity)

        val netUsers = netSource.getList(specification)
                .doOnNext { dbSource.putAll(it.mapNetListToDb()) }
                .flatMap { dbSource.getList(specification) }
                .onErrorResumeNext(dbSource.getList(specification))
                .map(List<GithubUserDbEntity>::mapDbListToEntity)

        return Observable.concat(dbUsers, netUsers)
    }

    override fun delete(model: GithubUserEntity) = dbSource.delete(model.mapEntityToDb())

    private fun getDbUser(specification: Specification) = dbSource.get(specification)
            .map(GithubUserDbEntity::mapDbToEntity)

    private fun getNetUser(specification: Specification): Observable<GithubUserEntity> {
        return netSource.get(specification)
                .doOnNext { dbSource.put(it.mapNetToDb()) }
                .flatMap { dbSource.get(specification) }
                .onErrorResumeNext(dbSource.get(specification))
                .map(GithubUserDbEntity::mapDbToEntity)
    }
}