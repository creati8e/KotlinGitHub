package chuprin.serg.kotlin_github.app.data.mapper

import chuprin.serg.kotlin_github.app.data.entity.UserDbEntity
import chuprin.serg.kotlin_github.app.data.entity.UserEntity
import chuprin.serg.kotlin_github.app.data.entity.UserNetworkEntity

fun UserDbEntity.mapDbToEntity(): UserEntity = UserEntity(login, avatarUrl, repos, followers, following)

fun UserNetworkEntity.mapNetToDb(): UserDbEntity = UserDbEntity(login, avatarUrl, repos, followers, following)

fun UserEntity.mapEntityToDb(): UserDbEntity = UserDbEntity(login, avatarUrl, repos, followers, following)

fun List<UserNetworkEntity>.mapNetListToDb(): List<UserDbEntity> = map(UserNetworkEntity::mapNetToDb).toList()

fun List<UserDbEntity>.mapDbListToEntity(): List<UserEntity> = map(UserDbEntity::mapDbToEntity).toList()