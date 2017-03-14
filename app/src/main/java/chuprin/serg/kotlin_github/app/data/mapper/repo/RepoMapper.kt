package chuprin.serg.kotlin_github.app.data.mapper.repo

import chuprin.serg.kotlin_github.app.data.entity.RepoDbEntity
import chuprin.serg.kotlin_github.app.data.entity.RepoEntity
import chuprin.serg.kotlin_github.app.data.entity.RepoNetworkEntity

fun RepoDbEntity.mapDbToEntity() = RepoEntity(name, size, isPrivate, desc, watchers, forks, issues, language)

fun RepoNetworkEntity.mapNetToEntity() = RepoEntity(name, size, isPrivate, desc, watchers, forks, issues, language)

fun RepoNetworkEntity.mapNetToDb() = RepoDbEntity(name, size, isPrivate, desc, watchers, forks, issues, language)

fun RepoEntity.mapEntityToDb() = RepoDbEntity(name, size, isPrivate, desc, watchers, forks, issues, language)

fun List<RepoEntity>.mapEntityListToDb(): List<RepoDbEntity> = map(RepoEntity::mapEntityToDb).toList()

fun List<RepoNetworkEntity>.mapNetListToDb(): List<RepoDbEntity> = map(RepoNetworkEntity::mapNetToDb).toList()

fun List<RepoDbEntity>.mapListDbToEntity(): List<RepoEntity> = map(RepoDbEntity::mapDbToEntity).toList()



