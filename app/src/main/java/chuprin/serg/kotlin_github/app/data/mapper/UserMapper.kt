package chuprin.serg.kotlin_github.app.data.mapper

import chuprin.serg.kotlin_github.app.data.entity.GithubUserDbEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.data.entity.GithubUserNetworkEntity

fun GithubUserDbEntity.mapDbToEntity(): GithubUserEntity = GithubUserEntity(id, login, avatarUrl, repos, followers, following)

fun GithubUserNetworkEntity.mapNetToDb(): GithubUserDbEntity = GithubUserDbEntity(id, login, avatarUrl, repos, followers, following)

fun List<GithubUserNetworkEntity>.mapNetListToDb(): List<GithubUserDbEntity> = map(GithubUserNetworkEntity::mapNetToDb).toList()

fun List<GithubUserDbEntity>.mapDbListToEntity(): List<GithubUserEntity> = map(GithubUserDbEntity::mapDbToEntity).toList()