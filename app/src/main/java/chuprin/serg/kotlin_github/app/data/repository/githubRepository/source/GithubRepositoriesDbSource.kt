package chuprin.serg.kotlin_github.app.data.repository.githubRepository.source

import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryDbEntity
import chuprin.serg.kotlin_github.app.data.repository.GenericDbSource

class GithubRepositoriesDbSource : GenericDbSource<GithubRepositoryDbEntity>() {

    override fun delete(model: GithubRepositoryDbEntity) = Unit
}