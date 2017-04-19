package chuprin.serg.kotlin_github.app.data.mapper

import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity

fun GithubRepositoryDbEntity.mapDbToEntity() = GithubRepositoryEntity(id, name, size, isPrivate, description ?: "", watchers, forks, issues, language ?: "", owner_id, owner_name)

fun GithubRepositoryNetworkEntity.mapNetToDb() = GithubRepositoryDbEntity(id, name, size, isPrivate, description, watchers, forks, issues, language, owner_id, owner_name)

fun GithubRepositoryEntity.mapEntityToDb() = GithubRepositoryDbEntity(id, name, size, isPrivate, description, watchers, forks, issues, language, owner_id, owner_name)

fun List<GithubRepositoryNetworkEntity>.mapNetListToDb(): List<GithubRepositoryDbEntity> = map(GithubRepositoryNetworkEntity::mapNetToDb).toList()

fun List<GithubRepositoryDbEntity>.mapListDbToEntity(): List<GithubRepositoryEntity> = map(GithubRepositoryDbEntity::mapDbToEntity).toList()



