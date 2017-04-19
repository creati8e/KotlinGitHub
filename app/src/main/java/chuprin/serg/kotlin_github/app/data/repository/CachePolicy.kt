package chuprin.serg.kotlin_github.app.data.repository

sealed class CachePolicy {
    class NET_ONLY : CachePolicy()
    class CACHE_ONLY : CachePolicy()
    class BOTH : CachePolicy()
}