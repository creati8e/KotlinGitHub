package chuprin.serg.khub.common.data.repository

sealed class CachePolicy {
    object BOTH : CachePolicy()
    object CACHE : CachePolicy()
    object NETWORK : CachePolicy()
}