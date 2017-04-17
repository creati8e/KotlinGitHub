package chuprin.serg.kotlin_github.app.data.repository.githubUser.source

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubUserDbEntity
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import com.vicpin.krealmextensions.saveAll
import rx.Observable
import rx.schedulers.Schedulers

class GithubUsersDbSource : Source<GithubUserDbEntity> {

    override fun getAll(): Observable<List<GithubUserDbEntity>> {
        return Observable.just(GithubUserDbEntity().queryAll()).subscribeOn(Schedulers.io())
    }

    override fun get(key: String): Observable<GithubUserDbEntity> {
        val cachedUser = GithubUserDbEntity().queryFirst { query -> query.equalTo("login", key) }
        return Observable.just(cachedUser ?: GithubUserDbEntity())
    }

    override fun put(model: GithubUserDbEntity) = model.save()

    override fun putAll(models: List<GithubUserDbEntity>) = models.saveAll()
}