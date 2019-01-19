package chuprin.serg.khub.common.data.mapper

import chuprin.serg.khub.common.data.database.dto.GithubRepositoryDbDto
import chuprin.serg.khub.common.data.entity.GithubRepositoryEntity
import chuprin.serg.khub.common.data.network.dto.GithubRepositoryNetworkDto

fun GithubRepositoryDbDto.mapToEntity() = GithubRepositoryEntity(
    id, name, size, private,
    description ?: "", watchers, stargazers, forks, issues, language ?: "", ownerId, ownerName, fork
)

fun GithubRepositoryNetworkDto.mapToDb() =
    GithubRepositoryDbDto(
        id,
        name,
        size,
        private,
        description ?: "",
        watchers,
        stargazers,
        forks,
        issues,
        language ?: "",
        ownerId,
        ownerName,
        fork
    )

fun GithubRepositoryEntity.mapToDb() =
    GithubRepositoryDbDto(
        id, name, size, private,
        description, watchers, stargazers, forks, issues, language, ownerId, ownerName, fork
    )

fun List<GithubRepositoryNetworkDto>.mapToDb(): List<GithubRepositoryDbDto> {
    return map(GithubRepositoryNetworkDto::mapToDb).toList()
}

fun List<GithubRepositoryDbDto>.mapToEntity(): List<GithubRepositoryEntity> {
    return map(GithubRepositoryDbDto::mapToEntity).toList()
}



