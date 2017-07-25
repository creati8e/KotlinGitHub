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
import chuprin.serg.kotlin_github.main.repositories.view.RepositoryRenderer
import chuprin.serg.kotlin_github.user.UserComponent
import chuprin.serg.kotlin_github.user.UserModule
import chuprin.serg.kotlin_github.user.presenter.UserPresenter

import kotlinx.android.synthetic.main.activity_user.*
import org.jetbrains.anko.toast
import serg.chuprin.adapter.MultiViewAdapter
import serg.chuprin.mvp_core.android.MvpActivity
import javax.inject.Inject

class UserActivity : MvpActivity<UserPresenter>(), UserView {

    @Inject lateinit var presenter: UserPresenter
    private val repositoriesAdapter: MultiViewAdapter = MultiViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repositoriesAdapter.registerRenderer(RepositoryRenderer())
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

    override fun createComponent(): Any {
        return KotApplication.component.userComponent(UserModule(intent.extras))
    }

    override fun componentClass(): Class<*> = UserComponent::class.java

    override fun showRepositories(repositories: List<GithubRepositoryEntity>) {
        repositoriesAdapter.setItems(repositories)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun showProgress(visible: Boolean) = progress.visibility(visible)

    override fun showMessage(message: Int) = toast(message)

}