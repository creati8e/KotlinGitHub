package chuprin.serg.kotlin_github.user.view

import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.R.layout.activity_user
import chuprin.serg.kotlin_github.app.mvp.view.MvpActivity
import chuprin.serg.kotlin_github.app.presentation.view.utils.load
import chuprin.serg.kotlin_github.user.presenter.UserPresenter
import kotlinx.android.synthetic.main.activity_user.*
import javax.inject.Inject

class UserActivity : MvpActivity<UserPresenter>(), UserView {

    @Inject internal lateinit var presenter: UserPresenter

    override fun getPresenter(): UserPresenter = presenter

    override fun getLayoutRes(): Int = activity_user

    override fun initPresenter() {
        presenter.setUser(intent.extras.getString("login"))
    }

    override fun showReposCount(count: String) {
        reposCount.text = count
    }

    override fun showFollowingCount(count: String) {
        followingCount.text = count
    }

    override fun showImage(image: String) {
        userImage.load(image, R.drawable.ic_user_placeholder)
    }

    override fun showLogin(login: String) {
        user.text = login
    }

    override fun showFollowersCount(count: String) {
        followersCount.text = count
    }
}