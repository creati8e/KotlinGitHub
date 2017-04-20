package chuprin.serg.kotlin_github.app.data.entity

import io.realm.RealmObject

open class GithubAccount(open var id: Int = -1,
                         open var login: String = "",
                         open var token: String = "",
                         open var active: Boolean = false) : RealmObject()