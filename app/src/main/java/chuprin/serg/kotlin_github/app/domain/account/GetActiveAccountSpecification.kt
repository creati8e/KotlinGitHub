package chuprin.serg.kotlin_github.app.domain.account

import chuprin.serg.kotlin_github.app.data.entity.GithubAccount
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import com.vicpin.krealmextensions.queryFirst
import rx.Observable

class GetActiveAccountSpecification : DbSpecification<GithubAccount> {

    override fun toDbResults(): Observable<GithubAccount> {
        val account = GithubAccount().queryFirst { it.equalTo("active", true) }
        return Observable.just(account ?: GithubAccount())
    }
}