package chuprin.serg.kotlin_github.app.data.repository.user.source

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.UserDbEntity
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import com.vicpin.krealmextensions.saveAll
import rx.Observable
import rx.schedulers.Schedulers

class UserDbSource : Source<UserDbEntity> {

    override fun getAll(): Observable<List<UserDbEntity>> {
        return Observable.just(UserDbEntity().queryAll()).subscribeOn(Schedulers.io())
    }

    override fun get(key: String): Observable<UserDbEntity> {
        val cachedUser = UserDbEntity().queryFirst { query -> query.equalTo("login", key) }
        return Observable.just(cachedUser ?: UserDbEntity())
    }

    override fun put(model: UserDbEntity) = model.save()

    override fun putAll(models: List<UserDbEntity>) = models.saveAll()
}