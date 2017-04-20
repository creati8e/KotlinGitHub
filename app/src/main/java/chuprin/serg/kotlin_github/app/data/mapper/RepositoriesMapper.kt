package chuprin.serg.kotlin_github.app.data.mapper

import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity

fun GithubRepositoryDbEntity.mapDbToEntity() = GithubRepositoryEntity(id, name, size, private,
        description ?: "", watchers, stargazers, forks, issues, language ?: "", ownerId, ownerName, fork)

fun GithubRepositoryNetworkEntity.mapNetToDb() = GithubRepositoryDbEntity(id, name, size, private,
        description ?: "", watchers, stargazers, forks, issues, language ?: "", ownerId, ownerName, fork)

fun GithubRepositoryEntity.mapEntityToDb() = GithubRepositoryDbEntity(id, name, size, private,
        description, watchers, stargazers, forks, issues, language, ownerId, ownerName, fork)

fun List<GithubRepositoryNetworkEntity>.mapNetListToDb(): List<GithubRepositoryDbEntity> {
    return map(GithubRepositoryNetworkEntity::mapNetToDb).toList()
}

fun List<GithubRepositoryDbEntity>.mapListDbToEntity(): List<GithubRepositoryEntity> {
    return map(GithubRepositoryDbEntity::mapDbToEntity).toList()
}



