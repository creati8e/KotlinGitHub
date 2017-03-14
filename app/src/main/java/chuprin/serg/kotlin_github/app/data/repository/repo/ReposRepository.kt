package chuprin.serg.kotlin_github.app.data.repository.repo

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.RepoDbEntity
import chuprin.serg.kotlin_github.app.data.entity.RepoEntity
import chuprin.serg.kotlin_github.app.data.entity.RepoNetworkEntity
import chuprin.serg.kotlin_github.app.data.mapper.repo.mapListDbToEntity
import chuprin.serg.kotlin_github.app.data.mapper.repo.mapNetListToDb
import rx.Observable
import javax.inject.Inject

class ReposRepository @Inject constructor(private val dbSource: Source<RepoDbEntity>,
                                          private val netSource: Source<RepoNetworkEntity>)
    : AbsRepository<RepoEntity> {

    override fun getAll(): Observable<List<RepoEntity>> {
        val dbRepos = dbSource.getAll().map(List<RepoDbEntity>::mapListDbToEntity)

        val netRepos = netSource.getAll()
                .doOnNext { dbSource.putAll(it.mapNetListToDb()) }
                .flatMap { dbSource.getAll() }
                .onErrorResumeNext(dbSource.getAll())
                .map(List<RepoDbEntity>::mapListDbToEntity)

        return Observable.concat(dbRepos, netRepos)
    }

    override fun get(key: String): Observable<RepoEntity> {
        throw UnsupportedOperationException("not implemented")
    }

}