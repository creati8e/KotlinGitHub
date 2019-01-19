package chuprin.serg.khub.common.data.database.specification.account

import chuprin.serg.khub.common.data.database.dto.GithubAccountDbDto
import chuprin.serg.khub.common.data.repository.specification.DbSpecification
import com.vicpin.krealmextensions.queryAll
import io.reactivex.Observable

class GetAllAccountsSpecification : DbSpecification<List<GithubAccountDbDto>> {

    override fun toDbResults(): Observable<List<GithubAccountDbDto>> {
        return Observable.just(GithubAccountDbDto().queryAll())
    }

}