package chuprin.serg.khub.common.data.database.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class GithubUserDbDto(
    @PrimaryKey open var id: Int = -1,
    open var login: String = "",
    open var avatarUrl: String = "",
    open var repos: Int = 0,
    open var followers: Int = 0,
    open var following: Int = 0
) : RealmObject()
