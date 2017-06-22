package chuprin.serg.kotlin_github.app.data.repository.githubUser.source

import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity
import chuprin.serg.kotlin_github.app.data.network.GithubUsersApi
import chuprin.serg.kotlin_github.app.data.repository.GenericNetworkSource
import javax.inject.Inject

class GithubUsersNetworkSource @Inject constructor(api: GithubUsersApi)
    : GenericNetworkSource<GithubUserNetworkEntity, GithubUsersApi>(api)