package chuprin.serg.khub

import android.app.Application
import chuprin.serg.khub.di.AppComponent
import chuprin.serg.khub.di.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

class KotApplication : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        Realm.init(applicationContext)
        Realm.setDefaultConfiguration(
            RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        )
        component = DaggerAppComponent.builder().context(applicationContext).build()
    }

}



