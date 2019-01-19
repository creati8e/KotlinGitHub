package chuprin.serg.khub.common.data.network.serialization

import chuprin.serg.khub.common.data.network.dto.GithubRepositoryNetworkDto
import com.google.gson.*
import java.lang.reflect.Type

class GithubRepositoryDeserializer : JsonDeserializer<GithubRepositoryNetworkDto> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GithubRepositoryNetworkDto {

        val root = json?.asJsonObject
        return Gson().fromJson(root, GithubRepositoryNetworkDto::class.java).apply {
            val owner: JsonObject? = root?.getAsJsonObject("owner")
            ownerId = owner?.getAsJsonPrimitive("id")?.asInt ?: 0
            ownerName = owner?.getAsJsonPrimitive("login")?.asString ?: ""
        }
    }
}