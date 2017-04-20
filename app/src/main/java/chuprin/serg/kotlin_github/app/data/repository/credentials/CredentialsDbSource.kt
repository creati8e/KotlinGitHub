package chuprin.serg.kotlin_github.app.data.repository.credentials

import chuprin.serg.kotlin_github.app.data.Source
import chuprin.serg.kotlin_github.app.data.entity.GithubAccount
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import chuprin.serg.kotlin_github.app.data.repository.specification.Specification
import com.vicpin.krealmextensions.delete
import com.vicpin.krealmextensions.save
import com.vicpin.krealmextensions.saveAll
import rx.Observable

@Suppress("UNCHECKED_CAST")
class CredentialsDbSource : Source<GithubAccount> {

    override fun putAll(models: List<GithubAccount>) = models.saveAll()

    override fun put(model: GithubAccount) = model.save()

    override fun get(specification: Specification): Observable<GithubAccount> {
        return (specification as DbSpecification<GithubAccount>).toDbResults()
    }

    override fun getList(specification: Specification): Observable<List<GithubAccount>> {
        return (specification as DbSpecification<List<GithubAccount>>).toDbResults()
    }

    override fun delete(model: GithubAccount) = model.delete { model.token }
}