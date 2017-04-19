package chuprin.serg.kotlin_github.app.data.entity

data class GithubRepositoryEntity(var id: Int = 0,
                                  var name: String = "",
                                  var size: String = "",
                                  var isPrivate: Boolean = false,
                                  var description: String = "",
                                  var watchers: Int = 0,
                                  var forks: Int = 0,
                                  var issues: Int = 0,
                                  var language: String = "",
                                  var owner_id: Int = 0,
                                  var owner_name: String = "")