package chuprin.serg.kotlin_github.main.view

import android.os.Bundle
import android.support.design.internal.NavigationMenuView
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import chuprin.serg.kotlin_github.KotApplication
import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.R.layout.activity_main
import chuprin.serg.kotlin_github.app.presentation.view.utils.load
import chuprin.serg.kotlin_github.main.MainComponent
import chuprin.serg.kotlin_github.main.MainModule
import chuprin.serg.kotlin_github.main.login.view.LoginActivity
import chuprin.serg.kotlin_github.main.presenter.MainPresenter
import chuprin.serg.kotlin_github.repositories.RepositoriesActivity
import kotlinx.android.synthetic.main.activity_main.*
import mvp_core.view.MvpActivity
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton
import javax.inject.Inject

class MainActivity : MvpActivity<MainPresenter>(), MainView {

    @Inject lateinit var presenter: MainPresenter
    lateinit var userLogin: TextView
    lateinit var userAvatar: ImageView
    lateinit var loginBtn: ImageView
    private val pagerAdapter: MainPagerAdapter = MainPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewpager.adapter = pagerAdapter
        tablayout.setupWithViewPager(viewpager)
        setSupportActionBar(toolbar)
        setupDrawer()
    }

    override fun onResume() {
        super.onResume()
        presenter.viewResumed()
    }

    private fun setupDrawer() {
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.app_name, R.string.app_name)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView?.getChildAt(0)?.apply {
            if (this is NavigationMenuView) {
                isVerticalScrollBarEnabled = false
                overScrollMode = View.OVER_SCROLL_NEVER
            }
        }

        val headerView = navigationView.getHeaderView(0)
        userLogin = headerView.findViewById(R.id.username) as TextView
        userAvatar = headerView.findViewById(R.id.headerImage) as ImageView
        loginBtn = headerView.findViewById(R.id.loginBtn) as ImageView
        loginBtn.setOnClickListener { presenter.loginBtnClicked() }

        navigationView.setNavigationItemSelectedListener {
            if (it.itemId == R.id.menu_item_my_repositories) {
                startActivity<RepositoriesActivity>()
            }
            true
        }
    }

    override fun getLayoutRes(): Int = activity_main

    override fun createComponent() = KotApplication.component.mainComponent(MainModule(intent.extras))

    override fun componentClass(): Class<*> = MainComponent::class.java

    override fun showUserLogin(login: String) {
        userLogin.text = login
    }

    override fun showUserAvatar(url: String) = userAvatar.load(url, R.drawable.github_logo)

    override fun showLogoutDialog() {
        alert("Are you really want to log out?") {
            yesButton { presenter.logout() }
            noButton { dialog -> dialog.dismiss() }
        }.show()
    }

    override fun showLogin() = startActivity<LoginActivity>()

    override fun showLoggedIn(enabled: Boolean) {
        loginBtn.setImageResource(if (enabled) R.drawable.ic_login else R.drawable.ic_logout)
        navigationView.menu.findItem(R.id.menu_item_my_repositories).isVisible = !enabled

    }
}
