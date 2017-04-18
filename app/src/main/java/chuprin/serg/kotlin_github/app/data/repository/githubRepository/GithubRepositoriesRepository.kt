package chuprin.serg.kotlin_github.app.data.repository.githubRepository

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity
import chuprin.serg.kotlin_github.app.data.mapper.mapDbToEntity
import chuprin.serg.kotlin_github.app.data.mapper.mapListDbToEntity
import chuprin.serg.kotlin_github.app.data.mapper.mapNetListToDb
import chuprin.serg.kotlin_github.app.data.mapper.mapNetToDb
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import rx.Observable
import javax.inject.Inject

class GithubRepositoriesRepository @Inject constructor(private val dbSource: Source<GithubRepositoryDbEntity>,
                                                       private val netSource: Source<GithubRepositoryNetworkEntity>)
    : AbsRepository<GithubRepositoryEntity> {

    override fun get(specification: Specification): Observable<GithubRepositoryEntity> {
        val dbRepo = dbSource.get(specification)
                .map { it.mapDbToEntity() }

        val netRepo = netSource.get(specification)
                .doOnNext { dbSource.put(it.mapNetToDb()) }
                .flatMap { dbSource.get(specification) }
                .onErrorResumeNext { dbSource.get(specification) }
                .map { it.mapDbToEntity() }

        return Observable.concat(dbRepo, netRepo)
    }

    override fun getList(specification: Specification): Observable<List<GithubRepositoryEntity>> {
        val dbRepos = dbSource.getList(specification)
                .map(List<GithubRepositoryDbEntity>::mapListDbToEntity)

        val netRepos = netSource.getList(specification)
                .doOnNext { dbSource.putAll(it.mapNetListToDb()) }
                .flatMap { dbSource.getList(specification) }
                .onErrorResumeNext(dbSource.getList(specification))
                .map(List<GithubRepositoryDbEntity>::mapListDbToEntity)

        return Observable.concat(dbRepos, netRepos)
    }
}