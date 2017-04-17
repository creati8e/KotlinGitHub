package chuprin.serg.kotlin_github.user.view

import android.os.Bundle
import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.R.layout.activity_user
import chuprin.serg.kotlin_github.app.di.MyApplication
import chuprin.serg.kotlin_github.app.presentation.view.utils.load
import chuprin.serg.kotlin_github.user.UserModule
import chuprin.serg.kotlin_github.user.presenter.UserPresenter
import chuprin.serg.mvpcore.view.MvpActivity
import kotlinx.android.synthetic.main.activity_user.*
import javax.inject.Inject

class UserActivity : MvpActivity<UserPresenter>(), UserView {
    override fun getLayoutRes(): Int = activity_user

    @Inject lateinit var presenter: UserPresenter

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

    override fun createComponent(state: Bundle?): Any {
        val bundle = Bundle()
        if (intent.extras != null) {
            bundle.putAll(intent.extras)
        }
        if (state != null) {
            bundle.putAll(state)
        }
        return MyApplication.component.userComponent(UserModule(bundle))
    }
}