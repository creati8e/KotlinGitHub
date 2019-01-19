package chuprin.serg.khub.common.data.database.datasource

import chuprin.serg.khub.common.data.database.dto.GithubAccountDbDto
import com.vicpin.krealmextensions.delete

class CredentialsDbDataSource : AbsDbDataSource<GithubAccountDbDto>() {

    override fun delete(model: GithubAccountDbDto) = model.delete { model.token }

}