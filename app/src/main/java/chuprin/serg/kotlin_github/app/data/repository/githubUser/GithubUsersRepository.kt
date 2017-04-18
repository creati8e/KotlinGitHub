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
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import rx.Observable
import javax.inject.Inject

class GithubUsersRepository @Inject constructor(
        val dbSource: Source<GithubUserDbEntity>,
        val netSource: Source<GithubUserNetworkEntity>) : AbsRepository<GithubUserEntity> {

    override fun get(specification: Specification): Observable<GithubUserEntity> {
        val dbUser = dbSource.get(specification).map(GithubUserDbEntity::mapDbToEntity)

        val netUser = netSource.get(specification)
                .doOnNext { dbSource.put(it.mapNetToDb()) }
                .flatMap { dbSource.get(specification) }
                .onErrorResumeNext(dbSource.get(specification))
                .map(GithubUserDbEntity::mapDbToEntity)

        return Observable.concat(dbUser, netUser)
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
}