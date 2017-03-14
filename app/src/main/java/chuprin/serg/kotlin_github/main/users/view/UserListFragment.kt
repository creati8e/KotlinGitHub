package chuprin.serg.kotlin_github.main.users.view

import chuprin.serg.kotlin_github.app.data.entity.UserEntity
import chuprin.serg.kotlin_github.app.mvp.MvpPresenter
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

    override fun getPresenter(): MvpPresenter<*> = presenter

    override fun createAdapter(): UsersAdapter = UsersAdapter()

    override fun onItemClicked(model: UserEntity, pos: Int) {
        activity.startActivity<UserActivity>("login" to model.login)
    }

}
