package chuprin.serg.kotlin_github.main.view

import android.os.Bundle
import android.support.design.internal.NavigationMenuView
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import android.widget.TextView
import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.R.layout.activity_main
import chuprin.serg.kotlin_github.app.di.MyApplication
import chuprin.serg.kotlin_github.main.MainModule
import chuprin.serg.kotlin_github.main.login.view.LoginActivity
import chuprin.serg.kotlin_github.main.presenter.MainPresenter
import chuprin.serg.mvpcore.view.MvpActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MainActivity : MvpActivity<MainPresenter>(), MainView {

    @Inject lateinit var presenter: MainPresenter
    lateinit var userLogin: TextView
    private val pagerAdapter: MainPagerAdapter = MainPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewpager.adapter = pagerAdapter
        tablayout.setupWithViewPager(viewpager)
        setSupportActionBar(toolbar)
        setupDrawer()
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

        userLogin = navigationView.getHeaderView(0).findViewById(R.id.username) as TextView
        userLogin.setOnClickListener { startActivity<LoginActivity>() }
    }

    override fun onResume() {
        super.onResume()
        presenter.viewResumed()
    }

    override fun getLayoutRes(): Int = activity_main

    override fun createComponent(state: Bundle?) = MyApplication.component.mainComponent(MainModule(state))

    override fun showUser(login: String) {
        userLogin.text = login
    }
}
