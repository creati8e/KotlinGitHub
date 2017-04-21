package chuprin.serg.kotlin_github.app.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class GithubAccount(open var id: Int = -1,
                         open var login: String = "",
                         @PrimaryKey open var token: String = "",
                         open var active: Boolean = false) : RealmObject()