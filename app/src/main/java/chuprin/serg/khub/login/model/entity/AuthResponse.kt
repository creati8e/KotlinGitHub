package chuprin.serg.khub.login.model.entity

import com.google.gson.annotations.SerializedName

data class AuthResponse(@SerializedName("access_token") val accessToken: String)