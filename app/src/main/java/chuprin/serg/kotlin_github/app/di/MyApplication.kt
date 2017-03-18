package chuprin.serg.kotlin_github.app.di

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class MyApplication : Application() {

    companion object {
        val component: AppComponent? = DaggerAppComponent.builder().dataModule(DataModule()).build()
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build())
    }
}



