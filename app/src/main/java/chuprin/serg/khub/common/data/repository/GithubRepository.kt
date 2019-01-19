package chuprin.serg.khub.common.data.repository

import chuprin.serg.khub.common.data.DataSource
import chuprin.serg.khub.common.data.Repository
import chuprin.serg.khub.common.data.database.dto.GithubRepositoryDbDto
import chuprin.serg.khub.common.data.entity.GithubRepositoryEntity
import chuprin.serg.khub.common.data.mapper.mapToDb
import chuprin.serg.khub.common.data.mapper.mapToEntity
import chuprin.serg.khub.common.data.network.dto.GithubRepositoryNetworkDto
import chuprin.serg.khub.common.data.repository.CachePolicy.*
import chuprin.serg.khub.common.data.repository.specification.Specification
import io.reactivex.Observable

class GithubRepository(
    private val dbDataSource: DataSource<GithubRepositoryDbDto>,
    private val netDataSource: DataSource<GithubRepositoryNetworkDto>
) : Repository<GithubRepositoryEntity> {

    override fun get(
        specification: Specification,
        cachePolicy: CachePolicy
    ): Observable<GithubRepositoryEntity> {
        return when (cachePolicy) {
            is BOTH -> Observable.concat(
                getSingleDbRepo(specification),
                getSingleNetRepo(specification)
            )
            is CACHE -> getSingleDbRepo(specification)
            is NETWORK -> getSingleNetRepo(specification)
        }
    }

    override fun getAll(
        specification: Specification,
        cachePolicy: CachePolicy
    ): Observable<List<GithubRepositoryEntity>> {
        return when (cachePolicy) {
            is BOTH -> Observable.concat(
                getDbRepos(specification),
                getNetRepos(specification)
            )
            is CACHE -> getDbRepos(specification)
            is NETWORK -> getNetRepos(specification)
        }
    }

    override fun put(model: GithubRepositoryEntity) = dbDataSource.put(model.mapToDb())

    override fun delete(model: GithubRepositoryEntity) = dbDataSource.delete(model.mapToDb())

    private fun getNetRepos(specification: Specification): Observable<List<GithubRepositoryEntity>> {
        return netDataSource
            .getAll(specification)
            .doOnNext { dbDataSource.putAll(it.mapToDb()) }
            .flatMap { dbDataSource.getAll(specification) }
            .onErrorResumeNext(dbDataSource.getAll(specification))
            .map(List<GithubRepositoryDbDto>::mapToEntity)
    }

    private fun getDbRepos(specification: Specification): Observable<List<GithubRepositoryEntity>> {
        return dbDataSource
            .getAll(specification)
            .map(List<GithubRepositoryDbDto>::mapToEntity)
    }

    private fun getSingleNetRepo(specification: Specification): Observable<GithubRepositoryEntity> {
        return netDataSource
            .get(specification)
            .doOnNext { dbDataSource.put(it.mapToDb()) }
            .flatMap { dbDataSource.get(specification) }
            .onErrorResumeNext { _: Throwable -> dbDataSource.get(specification) }
            .map(GithubRepositoryDbDto::mapToEntity)
    }

    private fun getSingleDbRepo(specification: Specification): Observable<GithubRepositoryEntity> {
        return dbDataSource
            .get(specification)
            .map(GithubRepositoryDbDto::mapToEntity)
    }

}