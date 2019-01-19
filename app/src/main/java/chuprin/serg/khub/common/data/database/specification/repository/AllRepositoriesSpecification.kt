package chuprin.serg.khub.common.data.database.specification.repository

import chuprin.serg.khub.common.data.database.dto.GithubRepositoryDbDto
import chuprin.serg.khub.common.data.network.api.GithubRepositoriesApi
import chuprin.serg.khub.common.data.network.dto.GithubRepositoryNetworkDto
import chuprin.serg.khub.common.data.repository.specification.DbSpecification
import chuprin.serg.khub.common.data.repository.specification.NetworkSpecification
import com.vicpin.krealmextensions.queryAll
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class AllRepositoriesSpecification :
    DbSpecification<List<GithubRepositoryDbDto>>,
    NetworkSpecification<GithubRepositoriesApi, List<GithubRepositoryNetworkDto>> {

    override fun toDbResults(): Observable<List<GithubRepositoryDbDto>> {
        return Observable
            .just(GithubRepositoryDbDto().queryAll())
            .subscribeOn(Schedulers.io())
    }

    override fun toNetResults(api: GithubRepositoriesApi) = api.getRepositories()

}