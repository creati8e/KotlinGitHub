package chuprin.serg.kotlin_github.app.data.entity

import com.google.gson.annotations.SerializedName

data class GithubUserNetworkEntity(var login: String = "",
                                   @SerializedName("avatar_url") var avatarUrl: String = "",
                                   @SerializedName("public_repos") var repos: Int = 0,
                                   var followers: Int = 0,
                                   var following: Int = 0)