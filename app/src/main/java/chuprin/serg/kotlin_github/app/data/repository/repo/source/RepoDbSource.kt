package chuprin.serg.kotlin_github.app.data.repository.repo.source

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.RepoDbEntity
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import com.vicpin.krealmextensions.saveAll
import rx.Observable
import rx.schedulers.Schedulers

class RepoDbSource : Source<RepoDbEntity> {

    override fun getAll(): Observable<List<RepoDbEntity>> {
        return Observable.just(RepoDbEntity().queryAll()).subscribeOn(Schedulers.io())
    }

    override fun get(key: String): Observable<RepoDbEntity> {
        val cachedRepo = RepoDbEntity().queryFirst { it.equalTo("name", key) }
        return Observable.just(cachedRepo ?: RepoDbEntity())
    }

    override fun putAll(models: List<RepoDbEntity>) = models.saveAll()

    override fun put(model: RepoDbEntity) = model.save()

}