package chuprin.serg.khub.common.data.database.specification.repository

import chuprin.serg.khub.common.data.database.dto.GithubRepositoryDbDto
import chuprin.serg.khub.common.data.repository.specification.DbSpecification
import com.vicpin.krealmextensions.query
import io.reactivex.Observable

class UserForkedRepositoriesSpecification(
    private val login: String
) : DbSpecification<List<GithubRepositoryDbDto>> {

    override fun toDbResults(): Observable<List<GithubRepositoryDbDto>> {
        return Observable.just(GithubRepositoryDbDto().query {
            it.equalTo("fork", true).equalTo("ownerName", login)
        })
    }

}