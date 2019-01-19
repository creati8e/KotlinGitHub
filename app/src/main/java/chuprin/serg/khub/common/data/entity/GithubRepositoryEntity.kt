package chuprin.serg.khub.common.data.entity

data class GithubRepositoryEntity(
    var id: Int = 0,
    var name: String = "",
    var size: String = "",
    var private: Boolean = false,
    var description: String = "",
    var watchers: Int = 0,
    val stargazers: Int = 0,
    var forks: Int = 0,
    var issues: Int = 0,
    var language: String = "",
    var ownerId: Int = 0,
    var ownerName: String = "",
    var fork: Boolean = false
)