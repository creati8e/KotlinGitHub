package chuprin.serg.kotlin_github.main.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import chuprin.serg.kotlin_github.app.presentation.view.BasePagerAdapter
import chuprin.serg.kotlin_github.main.repositories.view.RepositoriesListFragment
import chuprin.serg.kotlin_github.main.users.view.UsersListFragment

class MainPagerAdapter(fm: FragmentManager?) : BasePagerAdapter(fm) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> UsersListFragment()
            else -> RepositoriesListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Users"
            else -> "Repositories"
        }
    }
}
