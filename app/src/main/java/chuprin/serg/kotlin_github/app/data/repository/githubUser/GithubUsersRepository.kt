package chuprin.serg.kotlin_github.app.data.repository.githubUser

import chuprin.serg.kotlin_github.app.data.PaginationAbsRepository
import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubUserDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity
import chuprin.serg.kotlin_github.app.data.mapper.*
import chuprin.serg.kotlin_github.app.data.repository.CachePolicy
import chuprin.serg.kotlin_github.app.data.repository.CachePolicy.*
import chuprin.serg.kotlin_github.app.data.repository.specification.PaginationSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import io.reactivex.Observable
import javax.inject.Inject

class GithubUsersRepository @Inject constructor(
        private val dbSource: Source<GithubUserDbEntity>,
        private val netSource: Source<GithubUserNetworkEntity>) : PaginationAbsRepository<GithubUserEntity> {

    override fun put(model: GithubUserEntity) = dbSource.put(model.mapEntityToDb())

    override fun get(specification: Specification, cachePolicy: CachePolicy): Observable<GithubUserEntity> {
        when (cachePolicy) {
            is BOTH -> return Observable.concat(getDbUser(specification), getNetUser(specification))
            is CACHE_ONLY -> return getDbUser(specification)
            is NET_ONLY -> return getNetUser(specification)
        }
    }

    override fun getAll(specification: Specification,
                        cachePolicy: CachePolicy): Observable<List<GithubUserEntity>> {

        val dbUsers = dbSource.getAll(specification).map(List<GithubUserDbEntity>::mapDbListToEntity)

        val netUsers = netSource.getAll(specification)
                .doOnNext { dbSource.putAll(it.mapNetListToDb()) }
                .flatMap { dbSource.getAll(specification) }
                .onErrorResumeNext(dbSource.getAll(specification))
                .map(List<GithubUserDbEntity>::mapDbListToEntity)

        return Observable.concat(dbUsers, netUsers)
    }

    override fun getPage(specification: PaginationSpecification,
                         offset: Int): Observable<List<GithubUserEntity>> {

        specification.offset = offset
        return netSource.getAll(specification).map { it.mapNetToEntity() }
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