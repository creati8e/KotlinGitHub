package chuprin.serg.kotlin_github.main.users.view

import android.os.Bundle
import android.util.Log
import android.view.View
import chuprin.serg.kotlin_github.app.data.entity.UserEntity
import chuprin.serg.kotlin_github.app.di.MyApplication
import chuprin.serg.kotlin_github.main.DaggerMainComponent
import chuprin.serg.kotlin_github.main.MainModule
import chuprin.serg.kotlin_github.main.users.presenter.UserListPresenter
import chuprin.serg.kotlin_github.main.view.ListFragment
import chuprin.serg.kotlin_github.user.view.UserActivity
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class UserListFragment : ListFragment<UserEntity, UsersAdapter>(), UsersView {

    @Inject internal lateinit var presenter: UserListPresenter

    companion object {
        fun newInstance(): UserListFragment = UserListFragment()
    }

    override fun createAdapter(): UsersAdapter = UsersAdapter()

    override fun onItemClicked(model: UserEntity, pos: Int) {
        activity.startActivity<UserActivity>("login" to model.login)
    }

    override fun createComponent(savedState: Bundle?): Any {
        return DaggerMainComponent.builder()
                .appComponent(MyApplication.component)
                .mainModule(MainModule(savedState))
                .build()
    }

    override fun onViewCreated(view: View?, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
        Log.v("s", "s")
    }
}
