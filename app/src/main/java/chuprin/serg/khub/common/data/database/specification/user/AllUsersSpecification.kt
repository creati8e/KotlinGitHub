package chuprin.serg.khub.common.data.database.specification.user

import chuprin.serg.khub.common.data.database.dto.GithubUserDbDto
import chuprin.serg.khub.common.data.network.api.GithubUsersApi
import chuprin.serg.khub.common.data.network.dto.GithubUserNetworkDto
import chuprin.serg.khub.common.data.repository.specification.DbSpecification
import chuprin.serg.khub.common.data.repository.specification.NetworkSpecification
import chuprin.serg.khub.common.data.repository.specification.PaginationSpecification
import com.vicpin.krealmextensions.queryAll
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class AllUsersSpecification :
    DbSpecification<List<GithubUserDbDto>>,
    NetworkSpecification<GithubUsersApi, List<GithubUserNetworkDto>>,
    PaginationSpecification {

    override var offset: Int = 0

    override fun toDbResults(): Observable<List<GithubUserDbDto>> {
        return Observable.just(GithubUserDbDto().queryAll()).subscribeOn(Schedulers.io())
    }

    override fun toNetResults(api: GithubUsersApi): Observable<List<GithubUserNetworkDto>> {
        return api.getUsers(if (offset > 0) offset else null)
    }

}