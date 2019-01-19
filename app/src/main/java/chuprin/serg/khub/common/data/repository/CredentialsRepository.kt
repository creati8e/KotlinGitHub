package chuprin.serg.khub.common.data.repository

import chuprin.serg.khub.common.data.DataSource
import chuprin.serg.khub.common.data.Repository
import chuprin.serg.khub.common.data.database.dto.GithubAccountDbDto
import chuprin.serg.khub.common.data.repository.specification.Specification
import io.reactivex.Observable

class CredentialsRepository(
    private val dbDataSource: DataSource<GithubAccountDbDto>
) : Repository<GithubAccountDbDto> {

    override fun get(
        specification: Specification,
        cachePolicy: CachePolicy
    ): Observable<GithubAccountDbDto> {
        return dbDataSource.get(specification)
    }

    override fun getAll(
        specification: Specification,
        cachePolicy: CachePolicy
    ): Observable<List<GithubAccountDbDto>> {
        return dbDataSource.getAll(specification)
    }

    override fun put(model: GithubAccountDbDto) {
        dbDataSource.put(model)
    }

    override fun delete(model: GithubAccountDbDto) {
        dbDataSource.delete(model)
    }

}