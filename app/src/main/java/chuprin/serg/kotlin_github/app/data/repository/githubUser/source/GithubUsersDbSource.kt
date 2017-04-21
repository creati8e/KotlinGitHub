package chuprin.serg.kotlin_github.app.data.repository.githubUser.source

import chuprin.serg.kotlin_github.app.data.entity.GithubUserDbEntity
import chuprin.serg.kotlin_github.app.data.repository.GenericDbSource
import javax.inject.Inject

class GithubUsersDbSource @Inject constructor() : GenericDbSource<GithubUserDbEntity>() {

    override fun delete(model: GithubUserDbEntity) = Unit
}