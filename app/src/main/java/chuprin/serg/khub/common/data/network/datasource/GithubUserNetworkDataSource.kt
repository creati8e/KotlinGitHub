package chuprin.serg.khub.common.data.network.datasource

import chuprin.serg.khub.common.data.network.api.GithubUsersApi
import chuprin.serg.khub.common.data.network.dto.GithubUserNetworkDto

class GithubUserNetworkDataSource(
    api: GithubUsersApi
) : GenericNetworkDataSource<GithubUserNetworkDto, GithubUsersApi>(api)