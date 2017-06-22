package chuprin.serg.kotlin_github.app.data.repository.githubRepository.source

import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubRepositoriesApi
import chuprin.serg.kotlin_github.app.data.repository.GenericNetworkSource
import javax.inject.Inject

class GithubRepositoriesNetworkSource
@Inject constructor(api: GithubRepositoriesApi)
    : GenericNetworkSource<GithubRepositoryNetworkEntity, GithubRepositoriesApi>(api)