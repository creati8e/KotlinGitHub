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
        val dbRepo = dbSource.get(specification)
                .map { it.mapDbToEntity() }

        val netRepo = netSource.get(specification)
                .doOnNext { dbSource.put(it.mapNetToDb()) }
                .flatMap { dbSource.get(specification) }
                .onErrorResumeNext { _: Throwable? -> dbSource.get(specification) }
                .map { it.mapDbToEntity() }

        return Observable.concat(dbRepo, netRepo)
    }

    override fun getList(specification: Specification): Observable<List<GithubRepositoryEntity>> {
        val dbRepos = dbSource.getList(specification)
                .map(List<GithubRepositoryDbEntity>::mapListDbToEntity)

        val netRepos = netSource.getList(specification)
                .doOnNext { dbSource.putAll(it.mapNetListToDb()) }
                .flatMap { dbSource.getList(specification) }
                .doOnError { it.printStackTrace() }
                .onErrorResumeNext(dbSource.getList(specification))
                .map(List<GithubRepositoryDbEntity>::mapListDbToEntity)

        return Observable.concat(dbRepos, netRepos)
    }

    override fun put(model: GithubRepositoryEntity) = dbSource.put(model.mapEntityToDb())

    override fun delete(model: GithubRepositoryEntity) = dbSource.delete(model.mapEntityToDb())
}