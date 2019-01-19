package chuprin.serg.khub.common.data.repository

import chuprin.serg.khub.common.data.DataSource
import chuprin.serg.khub.common.data.PaginationRepository
import chuprin.serg.khub.common.data.database.dto.GithubUserDbDto
import chuprin.serg.khub.common.data.entity.GithubUserEntity
import chuprin.serg.khub.common.data.mapper.mapDbListToEntity
import chuprin.serg.khub.common.data.mapper.mapNetToEntity
import chuprin.serg.khub.common.data.mapper.mapToDb
import chuprin.serg.khub.common.data.mapper.mapToEntity
import chuprin.serg.khub.common.data.network.dto.GithubUserNetworkDto
import chuprin.serg.khub.common.data.repository.CachePolicy.*
import chuprin.serg.khub.common.data.repository.specification.PaginationSpecification
import chuprin.serg.khub.common.data.repository.specification.Specification
import io.reactivex.Observable

class GithubUserRepository(
    private val dbDataSource: DataSource<GithubUserDbDto>,
    private val netDataSource: DataSource<GithubUserNetworkDto>
) : PaginationRepository<GithubUserEntity> {

    override fun put(model: GithubUserEntity) = dbDataSource.put(model.mapToDb())

    override fun get(
        specification: Specification,
        cachePolicy: CachePolicy
    ): Observable<GithubUserEntity> {
        return when (cachePolicy) {
            is BOTH -> Observable.concat(getDbUser(specification), getNetUser(specification))
            is CACHE -> getDbUser(specification)
            is NETWORK -> getNetUser(specification)
        }
    }

    override fun getAll(
        specification: Specification,
        cachePolicy: CachePolicy
    ): Observable<List<GithubUserEntity>> {
        val dbUsers =
            dbDataSource.getAll(specification).map(List<GithubUserDbDto>::mapDbListToEntity)

        val netUsers = netDataSource
            .getAll(specification)
            .doOnNext { dbDataSource.putAll(it.mapToDb()) }
            .flatMap { dbDataSource.getAll(specification) }
            .onErrorResumeNext(dbDataSource.getAll(specification))
            .map(List<GithubUserDbDto>::mapDbListToEntity)

        return Observable.concat(dbUsers, netUsers)
    }

    override fun getPage(
        specification: PaginationSpecification,
        offset: Int
    ): Observable<List<GithubUserEntity>> {
        specification.offset = offset
        return netDataSource.getAll(specification).map { it.mapNetToEntity() }
    }

    override fun delete(model: GithubUserEntity) = dbDataSource.delete(model.mapToDb())

    private fun getDbUser(specification: Specification): Observable<GithubUserEntity> {
        return dbDataSource
            .get(specification)
            .map(GithubUserDbDto::mapToEntity)
    }

    private fun getNetUser(specification: Specification): Observable<GithubUserEntity> {
        return netDataSource
            .get(specification)
            .doOnNext { dbDataSource.put(it.mapToDb()) }
            .flatMap { dbDataSource.get(specification) }
            .onErrorResumeNext(dbDataSource.get(specification))
            .map(GithubUserDbDto::mapToEntity)
    }
}