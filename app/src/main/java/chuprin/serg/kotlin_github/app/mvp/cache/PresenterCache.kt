package chuprin.serg.kotlin_github.app.mvp.cache

import android.os.Bundle
import chuprin.serg.kotlin_github.app.mvp.MvpPresenter
import java.util.concurrent.atomic.AtomicInteger

object PresenterCache {
    private val cache: MutableMap<Int, MvpPresenter<*>?> = HashMap()
    private val BUNDLE_KEY: String = "BUNDLE_KEY"
    private var presenterId: AtomicInteger = AtomicInteger()

    fun get(bundle: Bundle): MvpPresenter<*>? {
        val key = bundle.getInt(BUNDLE_KEY)
        val presenter = cache[key]
        cache.remove(key)
        return presenter
    }

    fun save(bundle: Bundle, presenter: MvpPresenter<*>) {
        bundle.putInt(BUNDLE_KEY, presenterId.incrementAndGet())
        cache.put(presenterId.get(), presenter)
    }

    fun delete(presenter: MvpPresenter<*>) = cache.values.remove(presenter)

}

