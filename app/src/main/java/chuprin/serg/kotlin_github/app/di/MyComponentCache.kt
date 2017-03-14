package chuprin.serg.kotlin_github.app.di

import chuprin.serg.kotlin_github.app.mvp.ComponentCache
import chuprin.serg.kotlin_github.main.repos.view.ReposFragment
import chuprin.serg.kotlin_github.main.users.view.UserListFragment
import chuprin.serg.kotlin_github.user.view.UserActivity
import rx.functions.Func0

object MyComponentCache : ComponentCache() {
    init {
        val appComponent = DaggerAppComponent.builder().dataModule(DataModule()).build()
        register(Func0<Any>({ appComponent.getMainComponent() }),
                ReposFragment::class.java,
                UserListFragment::class.java)
        register(Func0 { appComponent.getUserComponent() },
                UserActivity::class.java)
    }
}
