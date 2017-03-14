package chuprin.serg.kotlin_github.app.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserDbEntity(@PrimaryKey open var login: String = "",
                        open var avatarUrl: String = "",
                        open var repos: Int = 0,
                        open var followers: Int = 0,
                        open var following: Int = 0) : RealmObject()
