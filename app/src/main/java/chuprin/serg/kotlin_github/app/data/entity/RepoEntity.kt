package chuprin.serg.kotlin_github.app.data.entity

data class RepoEntity(var name: String = "",
                      var size: String = "",
                      var isPrivate: Boolean = false,
                      var desc: String = "",
                      var watchers: Int = 0,
                      var forks: Int = 0,
                      var issues: Int = 0,
                      var language: String = "")