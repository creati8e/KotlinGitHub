package chuprin.serg.kotlin_github.app.domain.repositories

import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryDbEntity
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import com.vicpin.krealmextensions.query
import io.reactivex.Observable

class UserOwnRepositoriesSpecification(private val userLogin: String) : DbSpecification<List<GithubRepositoryDbEntity>> {

    override fun toDbResults(): Observable<List<GithubRepositoryDbEntity>> {
        return Observable.just(GithubRepositoryDbEntity()
                .query { it.equalTo("ownerName", userLogin).equalTo("fork", false) })
    }
}