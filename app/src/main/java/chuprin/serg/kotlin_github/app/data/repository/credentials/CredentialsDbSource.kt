package chuprin.serg.kotlin_github.app.data.repository.credentials

import chuprin.serg.kotlin_github.app.data.entity.GithubAccount
import chuprin.serg.kotlin_github.app.data.repository.GenericDbSource
import com.vicpin.krealmextensions.delete

class CredentialsDbSource : GenericDbSource<GithubAccount>() {

    override fun delete(model: GithubAccount) = model.delete { model.token }
}