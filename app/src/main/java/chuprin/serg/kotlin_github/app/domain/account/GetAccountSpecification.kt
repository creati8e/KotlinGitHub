package chuprin.serg.kotlin_github.app.domain.account

import chuprin.serg.kotlin_github.app.data.entity.GithubAccount
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import com.vicpin.krealmextensions.queryFirst
import io.reactivex.Observable

class GetAccountSpecification(private val login: String) : DbSpecification<GithubAccount> {

    override fun toDbResults(): Observable<GithubAccount> {
        val account = GithubAccount().queryFirst { it.equalTo("login", login) }
        return Observable.just(account ?: GithubAccount())
    }
}