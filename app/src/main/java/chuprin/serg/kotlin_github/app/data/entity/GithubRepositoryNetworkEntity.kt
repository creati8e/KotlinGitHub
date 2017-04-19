package chuprin.serg.kotlin_github.app.data.entity

class GithubRepositoryNetworkEntity(val id: Int = 0,
                                    val name: String = "",
                                    val size: String = "",
                                    val isPrivate: Boolean = false,
                                    val description: String? = null,
                                    val watchers: Int = 0,
                                    val forks: Int = 0,
                                    val issues: Int = 0,
                                    val language: String? = null,
                                    var owner_id: Int = 0,
                                    var owner_name: String = "")