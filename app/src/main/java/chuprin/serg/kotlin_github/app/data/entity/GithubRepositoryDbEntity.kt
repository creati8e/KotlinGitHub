package chuprin.serg.kotlin_github.app.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class GithubRepositoryDbEntity(@PrimaryKey open var id: Int = 0,
                                    open var name: String = "",
                                    open var size: String = "",
                                    open var isPrivate: Boolean = false,
                                    open var description: String? = null,
                                    open var watchers: Int = 0,
                                    open var forks: Int = 0,
                                    open var issues: Int = 0,
                                    open var language: String? = null,
                                    open var owner_id: Int = 0,
                                    open var owner_name: String = "") : RealmObject()