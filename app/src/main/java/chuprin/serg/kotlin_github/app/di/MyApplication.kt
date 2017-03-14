package chuprin.serg.kotlin_github.app.di

import android.app.Application
import chuprin.serg.kotlin_github.app.mvp.ComponentCache
import chuprin.serg.kotlin_github.app.mvp.ComponentCacheProvider
import io.realm.Realm
import io.realm.RealmConfiguration


class MyApplication : Application(), ComponentCacheProvider {

    lateinit internal var componentCache: ComponentCache

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        componentCache = MyComponentCache
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build())
    }

    override fun getComponentCache(): ComponentCache = componentCache

}



