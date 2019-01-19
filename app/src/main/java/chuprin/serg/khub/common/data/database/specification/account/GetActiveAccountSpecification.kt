package chuprin.serg.khub.common.data.database.specification.account

import chuprin.serg.khub.common.data.database.dto.GithubAccountDbDto
import chuprin.serg.khub.common.data.repository.specification.DbSpecification
import com.vicpin.krealmextensions.queryFirst
import io.reactivex.Observable

class GetActiveAccountSpecification : DbSpecification<GithubAccountDbDto> {

    override fun toDbResults(): Observable<GithubAccountDbDto> {
        val account = GithubAccountDbDto()
            .queryFirst { it.equalTo("active", true) }
        return Observable.just(account ?: GithubAccountDbDto())
    }

}