package chuprin.serg.kotlin_github.app.data.repository.githubRepository

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity
import chuprin.serg.kotlin_github.app.data.mapper.*
import chuprin.serg.kotlin_github.app.data.repository.CachePolicy
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import io.reactivex.Observable
import javax.inject.Inject

class GithubRepositoriesRepository
@Inject constructor(private val dbSource: Source<GithubRepositoryDbEntity>,
                    private val netSource: Source<GithubRepositoryNetworkEntity>) : AbsRepository<GithubRepositoryEntity> {

    override fun get(specification: Specification, cachePolicy: CachePolicy): Observable<GithubRepositoryEntity> {
        when (cachePolicy) {
            is CachePolicy.BOTH -> return Observable.concat(getSingleDbRepo(specification), getSingleNetRepo(specification))
            is CachePolicy.CACHE_ONLY -> return getSingleDbRepo(specification)
            is CachePolicy.NET_ONLY -> return getSingleNetRepo(specification)
        }
    }

    override fun getList(specification: Specification, cachePolicy: CachePolicy): Observable<List<GithubRepositoryEntity>> {
        when (cachePolicy) {
            is CachePolicy.BOTH -> return Observable.concat(getDbRepos(specification), getNetRepos(specification))
            is CachePolicy.CACHE_ONLY -> return getDbRepos(specification)
            is CachePolicy.NET_ONLY -> return getNetRepos(specification)
        }
    }

    override fun put(model: GithubRepositoryEntity) = dbSource.put(model.mapEntityToDb())

    override fun delete(model: GithubRepositoryEntity) = dbSource.delete(model.mapEntityToDb())

    private fun getNetRepos(specification: Specification): Observable<List<GithubRepositoryEntity>> {
        return netSource.getList(specification)
                .doOnNext { dbSource.putAll(it.mapNetListToDb()) }
                .flatMap { dbSource.getList(specification) }
                .onErrorResumeNext(dbSource.getList(specification))
                .map(List<GithubRepositoryDbEntity>::mapListDbToEntity)
    }

    private fun getDbRepos(specification: Specification) = dbSource.getList(specification)
            .map(List<GithubRepositoryDbEntity>::mapListDbToEntity)

    private fun getSingleNetRepo(specification: Specification): Observable<GithubRepositoryEntity> {
        return netSource.get(specification)
                .doOnNext { dbSource.put(it.mapNetToDb()) }
                .flatMap { dbSource.get(specification) }
                .onErrorResumeNext { _: Throwable? -> dbSource.get(specification) }
                .map { it.mapDbToEntity() }
    }

    private fun getSingleDbRepo(specification: Specification) = dbSource.get(specification)
            .map { it.mapDbToEntity() }


}