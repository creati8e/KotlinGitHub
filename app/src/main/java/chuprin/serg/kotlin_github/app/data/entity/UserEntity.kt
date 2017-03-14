package chuprin.serg.kotlin_github.app.data.entity

data class UserEntity(var login: String = "",
                      var avatarUrl: String = "",
                      var repos: Int = 0,
                      var followers: Int = 0,
                      var following: Int = 0)

