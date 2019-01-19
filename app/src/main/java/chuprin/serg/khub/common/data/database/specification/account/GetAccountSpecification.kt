package chuprin.serg.khub.common.data.database.specification.account

import chuprin.serg.khub.common.data.database.dto.GithubAccountDbDto
import chuprin.serg.khub.common.data.repository.specification.DbSpecification
import com.vicpin.krealmextensions.queryFirst
import io.reactivex.Observable

class GetAccountSpecification(private val login: String) : DbSpecification<GithubAccountDbDto> {

    override fun toDbResults(): Observable<GithubAccountDbDto> {
        val account = GithubAccountDbDto()
            .queryFirst { it.equalTo("login", login) }
        return Observable.just(account ?: GithubAccountDbDto())
    }

}