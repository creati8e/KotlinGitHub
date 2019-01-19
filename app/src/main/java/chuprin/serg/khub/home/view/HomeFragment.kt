package chuprin.serg.khub.home.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.forEach
import chuprin.serg.khub.R
import chuprin.serg.khub.common.presentation.extensions.*
import chuprin.serg.khub.common.presentation.view.BaseFragment
import chuprin.serg.khub.home.di.HomeComponent
import chuprin.serg.khub.home.model.UserState
import chuprin.serg.khub.login.view.LoginActivity
import chuprin.serg.khub.main.MainPagerAdapter
import chuprin.serg.khub.repositories.RepositoriesActivity
import com.google.android.material.internal.NavigationMenuView
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

/**
 * @author Sergey Chuprin
 */
class HomeFragment : BaseFragment() {

    override val layoutResId: Int = R.layout.fragment_home

    lateinit var loginBtn: ImageView

    private lateinit var userLogin: TextView
    private lateinit var userAvatar: ImageView

    private lateinit var pagerAdapter: MainPagerAdapter

    private val viewModel by viewModel { componentViewModel.component.viewModel }
    private val componentViewModel by componentViewModel { HomeComponent.get() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagerAdapter = MainPagerAdapter(childFragmentManager)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        setupToolbar(toolbar)
        setupDrawer()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.userLiveData.observe { showCurrentUser(it) }
    }

    private fun showCurrentUser(state: UserState) {
        userLogin.text = state.login
        userAvatar.load(state.avatarUrl, R.drawable.ic_user_placeholder)

        navigationView.menu.forEach { it.isVisible = state.logged }
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
            requireActivity(),
            drawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_name
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.getChildAt(0)?.apply {
            (this as? NavigationMenuView)?.apply {
                isVerticalScrollBarEnabled = false
                overScrollMode = View.OVER_SCROLL_NEVER
            }
        }

        with(navigationView.getHeaderView(0)) {
            loginBtn = findViewById(R.id.loginBtn)
            userLogin = findViewById(R.id.username)
            userAvatar = findViewById(R.id.headerImage)
        }

        loginBtn.onClick {
            if (viewModel.isLoggedId) {
                showLogoutDialog()
            } else {
                requireActivity().startActivity<LoginActivity>()
            }
        }

        navigationView.setNavigationItemSelectedListener {
            if (it.itemId == R.id.menu_item_my_repositories) {
                requireActivity().startActivity<RepositoriesActivity>()
            }
            true
        }
    }

    private fun showLogoutDialog() {
        requireActivity().alert("Are you really want to log out?") {
            yesButton { viewModel.logout() }
            noButton(DialogInterface::dismiss)
        }.show()
    }

}