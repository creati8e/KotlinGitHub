package chuprin.serg.kotlin_github.app.data.entity

import com.google.gson.annotations.SerializedName

data class GithubUserNetworkEntity(val id: Int = 0,
                                   val login: String = "",
                                   @SerializedName("avatar_url") val avatarUrl: String = "",
                                   @SerializedName("public_repos") val repos: Int = 0,
                                   val followers: Int = 0,
                                   val following: Int = 0)