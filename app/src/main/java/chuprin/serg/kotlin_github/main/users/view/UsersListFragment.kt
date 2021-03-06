package chuprin.serg.kotlin_github.main.users.view

import android.os.Bundle
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.di.MyApplication
import chuprin.serg.kotlin_github.main.MainModule
import chuprin.serg.kotlin_github.main.users.presenter.UsersListPresenter
import chuprin.serg.kotlin_github.main.view.ListFragment
import chuprin.serg.kotlin_github.user.view.UserActivity
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class UsersListFragment : ListFragment<GithubUserEntity, UsersAdapter>(), UsersListView {

    @Inject lateinit var presenter: UsersListPresenter

    companion object {
        fun newInstance(): UsersListFragment = UsersListFragment()
    }

    override fun createAdapter(): UsersAdapter = UsersAdapter()

    override fun onItemClicked(model: GithubUserEntity, pos: Int) {
        activity.startActivity<UserActivity>("login" to model.login)
    }

    override fun createComponent(savedState: Bundle?): Any {
        return MyApplication.component.mainComponent(MainModule(savedState))
    }
}
