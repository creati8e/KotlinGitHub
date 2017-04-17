package chuprin.serg.kotlin_github.app.data.mapper

import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity

fun GithubRepositoryDbEntity.mapDbToEntity() = GithubRepositoryEntity(name, size, isPrivate, desc, watchers, forks, issues, language)

fun GithubRepositoryNetworkEntity.mapNetToEntity() = GithubRepositoryEntity(name, size, isPrivate, desc, watchers, forks, issues, language)

fun GithubRepositoryNetworkEntity.mapNetToDb() = GithubRepositoryDbEntity(name, size, isPrivate, desc, watchers, forks, issues, language)

fun GithubRepositoryEntity.mapEntityToDb() = GithubRepositoryDbEntity(name, size, isPrivate, desc, watchers, forks, issues, language)

fun List<GithubRepositoryEntity>.mapEntityListToDb(): List<GithubRepositoryDbEntity> = map(GithubRepositoryEntity::mapEntityToDb).toList()

fun List<GithubRepositoryNetworkEntity>.mapNetListToDb(): List<GithubRepositoryDbEntity> = map(GithubRepositoryNetworkEntity::mapNetToDb).toList()

fun List<GithubRepositoryDbEntity>.mapListDbToEntity(): List<GithubRepositoryEntity> = map(GithubRepositoryDbEntity::mapDbToEntity).toList()



