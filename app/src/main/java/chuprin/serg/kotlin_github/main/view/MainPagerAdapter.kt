package chuprin.serg.kotlin_github.main.view

import android.support.v4.app.FragmentManager
import chuprin.serg.kotlin_github.app.presentation.view.BasePagerAdapter
import chuprin.serg.kotlin_github.app.presentation.view.utils.instanceOf
import chuprin.serg.kotlin_github.main.users.view.UsersListFragment

class MainPagerAdapter(fm: FragmentManager?) : BasePagerAdapter(fm) {

    override fun getCount(): Int = 1

    override fun getItem(position: Int) = instanceOf<UsersListFragment>()

    override fun getPageTitle(position: Int) = "Users"
}
