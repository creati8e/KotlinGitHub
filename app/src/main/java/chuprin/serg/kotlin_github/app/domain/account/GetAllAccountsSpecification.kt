package chuprin.serg.kotlin_github.app.domain.account

import chuprin.serg.kotlin_github.app.data.entity.GithubAccount
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import com.vicpin.krealmextensions.queryAll
import io.reactivex.Observable

class GetAllAccountsSpecification : DbSpecification<List<GithubAccount>> {

    override fun toDbResults(): Observable<List<GithubAccount>> = Observable.just(GithubAccount().queryAll())
}