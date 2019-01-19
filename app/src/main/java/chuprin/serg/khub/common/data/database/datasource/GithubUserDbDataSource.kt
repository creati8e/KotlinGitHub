package chuprin.serg.khub.common.data.database.datasource

import chuprin.serg.khub.common.data.database.dto.GithubUserDbDto

class GithubUserDbDataSource : AbsDbDataSource<GithubUserDbDto>() {

    override fun delete(model: GithubUserDbDto) = Unit

}