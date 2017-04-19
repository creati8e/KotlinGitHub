package chuprin.serg.kotlin_github.user.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import chuprin.serg.kotlin_github.KotApplication
import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.R.layout.activity_user
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.presentation.view.utils.load
import chuprin.serg.kotlin_github.app.presentation.view.utils.visibility
import chuprin.serg.kotlin_github.main.repositories.view.RepositoryAdapter
import chuprin.serg.kotlin_github.user.UserModule
import chuprin.serg.kotlin_github.user.presenter.UserPresenter
import chuprin.serg.mvpcore.view.MvpActivity
import kotlinx.android.synthetic.main.activity_user.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class UserActivity : MvpActivity<UserPresenter>(), UserView {

    @Inject lateinit var presenter: UserPresenter
    private val repositoriesAdapter: RepositoryAdapter = RepositoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView.adapter = repositoriesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        setSupportActionBar(toolbar)
        supportActionBar?.apply { setDisplayHomeAsUpEnabled(true); title = "" }
    }

    override fun showReposCount(count: String) {
        reposCount.text = count
    }

    override fun getLayoutRes(): Int = activity_user

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
        return KotApplication.component.userComponent(UserModule(bundle))
    }

    override fun showRepositories(repositories: List<GithubRepositoryEntity>) {
        repositoriesAdapter.setData(repositories)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun showProgress(visible: Boolean) = progress.visibility(visible)

    override fun showMessage(message: Int) = toast(message)

}