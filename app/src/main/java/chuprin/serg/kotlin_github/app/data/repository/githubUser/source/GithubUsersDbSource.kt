package chuprin.serg.kotlin_github.app.data.repository.githubUser.source

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubUserDbEntity
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import com.vicpin.krealmextensions.save
import com.vicpin.krealmextensions.saveAll
import rx.Observable
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class GithubUsersDbSource @Inject constructor() : Source<GithubUserDbEntity> {

    override fun getList(specification: Specification): Observable<List<GithubUserDbEntity>> {
        return (specification as DbSpecification<List<GithubUserDbEntity>>).toDbResults()
    }

    override fun get(specification: Specification): Observable<GithubUserDbEntity> {
        return (specification as DbSpecification<GithubUserDbEntity>).toDbResults()
    }

    override fun delete(model: GithubUserDbEntity) = Unit

    override fun put(model: GithubUserDbEntity) = model.save()

    override fun putAll(models: List<GithubUserDbEntity>) = models.saveAll()
}