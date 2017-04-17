package chuprin.serg.kotlin_github.app.data.repository.githubRepository

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity
import chuprin.serg.kotlin_github.app.data.mapper.mapListDbToEntity
import chuprin.serg.kotlin_github.app.data.mapper.mapNetListToDb
import rx.Observable
import javax.inject.Inject

class GithubRepositoriesRepository @Inject constructor(private val dbSource: Source<GithubRepositoryDbEntity>,
                                                       private val netSource: Source<GithubRepositoryNetworkEntity>)
    : AbsRepository<GithubRepositoryEntity> {

    override fun getAll(): Observable<List<GithubRepositoryEntity>> {
        val dbRepos = dbSource.getAll().map(List<GithubRepositoryDbEntity>::mapListDbToEntity)

        val netRepos = netSource.getAll()
                .doOnNext { dbSource.putAll(it.mapNetListToDb()) }
                .flatMap { dbSource.getAll() }
                .onErrorResumeNext(dbSource.getAll())
                .map(List<GithubRepositoryDbEntity>::mapListDbToEntity)

        return Observable.concat(dbRepos, netRepos)
    }

    override fun get(key: String): Observable<GithubRepositoryEntity> {
        throw UnsupportedOperationException("not implemented")
    }

}