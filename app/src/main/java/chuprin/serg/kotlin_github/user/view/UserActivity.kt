package chuprin.serg.kotlin_github.user.view

import android.os.Bundle
import android.util.Log
import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.R.layout.activity_user
import chuprin.serg.kotlin_github.app.di.MyApplication
import chuprin.serg.kotlin_github.app.mvp.view.MvpActivity
import chuprin.serg.kotlin_github.app.presentation.view.utils.load
import chuprin.serg.kotlin_github.user.DaggerUserComponent
import chuprin.serg.kotlin_github.user.UserModule
import chuprin.serg.kotlin_github.user.presenter.UserPresenter
import kotlinx.android.synthetic.main.activity_user.*
import javax.inject.Inject

class UserActivity : MvpActivity<UserPresenter>(activity_user), UserView {

    @Inject internal lateinit var presenter: UserPresenter

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

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        Log.v("s", "s")
    }

    override fun createComponent(state: Bundle?): Any {
        return DaggerUserComponent.builder()
                .appComponent(MyApplication.component)
                .userModule(UserModule(state))
                .build()
    }
}