package chuprin.serg.kotlin_github.app.data.mapper

import chuprin.serg.kotlin_github.app.data.entity.GithubUserDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity

fun GithubUserDbEntity.mapDbToEntity(): GithubUserEntity {
    return GithubUserEntity(id, login, avatarUrl, repos, followers, following)
}

fun GithubUserNetworkEntity.mapNetToDb(): GithubUserDbEntity {
    return GithubUserDbEntity(id, login, avatarUrl ?: "", repos, followers, following)
}

fun GithubUserNetworkEntity.mapToEntity(): GithubUserEntity {
    return GithubUserEntity(id, login, avatarUrl ?: "", repos, followers, following)
}

fun GithubUserEntity.mapEntityToDb(): GithubUserDbEntity {
    return GithubUserDbEntity(id, login, avatarUrl, repos, followers, following)
}

fun List<GithubUserNetworkEntity>.mapNetListToDb(): List<GithubUserDbEntity> {
    return map(GithubUserNetworkEntity::mapNetToDb).toList()
}

fun List<GithubUserDbEntity>.mapDbListToEntity(): List<GithubUserEntity> {
    return map(GithubUserDbEntity::mapDbToEntity).toList()
}

fun List<GithubUserNetworkEntity>.mapNetToEntity(): List<GithubUserEntity> {
    return map(GithubUserNetworkEntity::mapToEntity)
}