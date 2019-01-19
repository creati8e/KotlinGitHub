package chuprin.serg.khub.common.data.network.dto

import com.google.gson.annotations.SerializedName

data class GithubUserNetworkDto(
    val id: Int = -1,
    val login: String = "",
    @SerializedName("avatar_url") val avatarUrl: String? = null,
    @SerializedName("public_repos") val repos: Int = 0,
    val followers: Int = 0,
    val following: Int = 0
)