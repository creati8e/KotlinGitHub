package chuprin.serg.kotlin_github.app.data.repository.githubRepository.source

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryDbEntity
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import com.vicpin.krealmextensions.save
import com.vicpin.krealmextensions.saveAll
import rx.Observable

@Suppress("UNCHECKED_CAST")
class GithubRepositoriesDbSource : Source<GithubRepositoryDbEntity> {

    override fun getList(specification: Specification): Observable<List<GithubRepositoryDbEntity>> {
        val spec = specification as DbSpecification<List<GithubRepositoryDbEntity>>
        return spec.toDbResults()
    }

    override fun get(specification: Specification): Observable<GithubRepositoryDbEntity> {
        val spec = specification as DbSpecification<GithubRepositoryDbEntity>
        return spec.toDbResults()
    }

    override fun putAll(models: List<GithubRepositoryDbEntity>) = models.saveAll()

    override fun put(model: GithubRepositoryDbEntity) = model.save()

}