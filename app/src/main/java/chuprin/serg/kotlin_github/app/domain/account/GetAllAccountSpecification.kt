package chuprin.serg.kotlin_github.app.domain.account

import chuprin.serg.kotlin_github.app.data.entity.GithubAccount
import chuprin.serg.kotlin_github.app.data.repository.specification.DbSpecification
import com.vicpin.krealmextensions.queryAll
import rx.Observable

class GetAllAccountSpecification : DbSpecification<List<GithubAccount>> {

    override fun toDbResults(): Observable<List<GithubAccount>> = Observable.just(GithubAccount().queryAll())
}