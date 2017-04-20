package chuprin.serg.kotlin_github.app.data.entity

import com.google.gson.annotations.SerializedName

class GithubRepositoryNetworkEntity(val id: Int = 0,
                                    val name: String = "",
                                    val size: String = "",
                                    val private: Boolean = false,
                                    val description: String? = null,
                                    @SerializedName("watchers_count") val watchers: Int = 0,
                                    @SerializedName("stargazers_count") val stargazers: Int = 0,
                                    @SerializedName("forks_count") val forks: Int = 0,
                                    val issues: Int = 0,
                                    val language: String? = null,
                                    var ownerId: Int = 0,
                                    var ownerName: String = "",
                                    var fork: Boolean = false)