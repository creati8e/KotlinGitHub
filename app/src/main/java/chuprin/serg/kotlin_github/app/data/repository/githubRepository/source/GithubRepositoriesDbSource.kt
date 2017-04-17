package chuprin.serg.kotlin_github.app.data.repository.githubRepository.source

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryDbEntity
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import com.vicpin.krealmextensions.saveAll
import rx.Observable
import rx.schedulers.Schedulers

class GithubRepositoriesDbSource : Source<GithubRepositoryDbEntity> {

    override fun getAll(): Observable<List<GithubRepositoryDbEntity>> {
        return Observable.just(GithubRepositoryDbEntity().queryAll()).subscribeOn(Schedulers.io())
    }

    override fun get(key: String): Observable<GithubRepositoryDbEntity> {
        val cachedRepo = GithubRepositoryDbEntity().queryFirst { it.equalTo("name", key) }
        return Observable.just(cachedRepo ?: GithubRepositoryDbEntity())
    }

    override fun putAll(models: List<GithubRepositoryDbEntity>) = models.saveAll()

    override fun put(model: GithubRepositoryDbEntity) = model.save()

}