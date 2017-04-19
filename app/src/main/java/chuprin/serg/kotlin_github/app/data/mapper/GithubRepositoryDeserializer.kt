package chuprin.serg.kotlin_github.app.data.mapper

import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryNetworkEntity
import com.google.gson.*
import java.lang.reflect.Type

class GithubRepositoryDeserializer : JsonDeserializer<GithubRepositoryNetworkEntity> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?,
                             context: JsonDeserializationContext?): GithubRepositoryNetworkEntity {

        val root = json?.asJsonObject
        return Gson().fromJson(root, GithubRepositoryNetworkEntity::class.java).apply {
            val owner: JsonObject? = root?.getAsJsonObject("owner")
            owner_id = owner?.getAsJsonPrimitive("id")?.asInt ?: 0
            owner_name = owner?.getAsJsonPrimitive("login")?.asString ?: ""
        }
    }
}