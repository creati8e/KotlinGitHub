package chuprin.serg.kotlin_github.app.data.repository.credentials

import android.content.SharedPreferences
import javax.inject.Inject

class CredentialsRepository @Inject constructor(private val preferences: SharedPreferences) {
    companion object {
        const private val ACCESS_TOKEN = "ACCESS_TOKEN"
    }

    fun put(token: String) = preferences.edit().putString(ACCESS_TOKEN, token).apply()

    fun get(): String = preferences.getString(ACCESS_TOKEN, "")
}