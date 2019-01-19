package chuprin.serg.khub.common.data.mapper

import chuprin.serg.khub.common.data.database.dto.GithubUserDbDto
import chuprin.serg.khub.common.data.entity.GithubUserEntity
import chuprin.serg.khub.common.data.network.dto.GithubUserNetworkDto

fun GithubUserDbDto.mapToEntity(): GithubUserEntity {
    return GithubUserEntity(id, login, avatarUrl, repos, followers, following)
}

fun GithubUserNetworkDto.mapToDb(): GithubUserDbDto {
    return GithubUserDbDto(
        id,
        login,
        avatarUrl ?: "",
        repos,
        followers,
        following
    )
}

fun GithubUserNetworkDto.mapToEntity(): GithubUserEntity {
    return GithubUserEntity(id, login, avatarUrl ?: "", repos, followers, following)
}

fun GithubUserEntity.mapToDb(): GithubUserDbDto {
    return GithubUserDbDto(
        id,
        login,
        avatarUrl,
        repos,
        followers,
        following
    )
}

fun List<GithubUserNetworkDto>.mapToDb(): List<GithubUserDbDto> {
    return map(GithubUserNetworkDto::mapToDb).toList()
}

fun List<GithubUserDbDto>.mapDbListToEntity(): List<GithubUserEntity> {
    return map(GithubUserDbDto::mapToEntity).toList()
}

fun List<GithubUserNetworkDto>.mapNetToEntity(): List<GithubUserEntity> {
    return map(GithubUserNetworkDto::mapToEntity)
}