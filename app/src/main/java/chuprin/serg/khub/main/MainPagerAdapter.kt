package chuprin.serg.khub.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import chuprin.serg.khub.common.presentation.extensions.instanceOf
import chuprin.serg.khub.common.presentation.view.adapter.BasePagerAdapter
import chuprin.serg.khub.users.view.UsersListFragment

class MainPagerAdapter(fm: FragmentManager) : BasePagerAdapter(fm) {

    override fun getCount(): Int = 1

    override fun getPageTitle(position: Int) = "Users"

    override fun getItem(position: Int): Fragment =
        instanceOf<UsersListFragment>()

}
