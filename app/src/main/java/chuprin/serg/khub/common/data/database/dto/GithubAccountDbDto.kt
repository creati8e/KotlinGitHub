package chuprin.serg.khub.common.data.database.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class GithubAccountDbDto(
    open var id: Int = -1,
    open var login: String = "",
    @PrimaryKey open var token: String = "",
    open var active: Boolean = false
) : RealmObject()