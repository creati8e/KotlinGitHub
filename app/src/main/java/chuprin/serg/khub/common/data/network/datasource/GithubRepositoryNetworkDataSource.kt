package chuprin.serg.khub.common.data.network.datasource

import chuprin.serg.khub.common.data.network.api.GithubRepositoriesApi
import chuprin.serg.khub.common.data.network.dto.GithubRepositoryNetworkDto

class GithubRepositoryNetworkDataSource(
    api: GithubRepositoriesApi
) : GenericNetworkDataSource<GithubRepositoryNetworkDto, GithubRepositoriesApi>(api)