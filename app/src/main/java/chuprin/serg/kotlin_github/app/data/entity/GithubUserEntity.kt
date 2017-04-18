package chuprin.serg.kotlin_github.app.data.entity

data class GithubUserEntity(var id: Int = 0,
                            var login: String = "",
                            var avatarUrl: String = "",
                            var repos: Int = 0,
                            var followers: Int = 0,
                            var following: Int = 0)

