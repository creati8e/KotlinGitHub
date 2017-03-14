package chuprin.serg.kotlin_github.app.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RepoDbEntity(@PrimaryKey open var name: String = "",
                        open var size: String = "",
                        open var isPrivate: Boolean = false,
                        open var desc: String = "",
                        open var watchers: Int = 0,
                        open var forks: Int = 0,
                        open var issues: Int = 0,
                        open var language: String = "") : RealmObject()