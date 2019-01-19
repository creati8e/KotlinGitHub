package chuprin.serg.khub.common.data.database.datasource

import chuprin.serg.khub.common.data.database.dto.GithubRepositoryDbDto

class GithubRepositoryDbDataSource : AbsDbDataSource<GithubRepositoryDbDto>() {

    override fun delete(model: GithubRepositoryDbDto) = Unit

}