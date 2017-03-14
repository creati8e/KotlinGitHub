package chuprin.serg.kotlin_github.app.data.repository.user

import chuprin.serg.kotlin_github.app.data.AbsRepository
import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.UserDbEntity
import chuprin.serg.kotlin_github.app.data.entity.UserEntity
import chuprin.serg.kotlin_github.app.data.entity.UserNetworkEntity
import chuprin.serg.kotlin_github.app.data.mapper.mapDbListToEntity
import chuprin.serg.kotlin_github.app.data.mapper.mapDbToEntity
import chuprin.serg.kotlin_github.app.data.mapper.mapNetListToDb
import chuprin.serg.kotlin_github.app.data.mapper.mapNetToDb
import rx.Observable
import javax.inject.Inject

class UsersRepository @Inject constructor(
        val dbSource: Source<UserDbEntity>,
        val netSource: Source<UserNetworkEntity>) : AbsRepository<UserEntity> {

    override fun getAll(): Observable<List<UserEntity>> {
        val dbUsers = dbSource.getAll().map(List<UserDbEntity>::mapDbListToEntity)

        val netUsers = netSource.getAll()
                .doOnNext { dbSource.putAll(it.mapNetListToDb()) }
                .flatMap { dbSource.getAll() }
                .onErrorResumeNext(dbSource.getAll())
                .map(List<UserDbEntity>::mapDbListToEntity)

        return Observable.concat(dbUsers, netUsers)
    }

    override fun get(key: String): Observable<UserEntity> {
        val dbUser = dbSource.get(key).map(UserDbEntity::mapDbToEntity)

        val netUser = netSource.get(key)
                .doOnNext { dbSource.put(it.mapNetToDb()) }
                .flatMap { dbSource.get(key) }
                .onErrorResumeNext(dbSource.get(key))
                .map(UserDbEntity::mapDbToEntity)

        return Observable.concat(dbUser, netUser)
    }
}