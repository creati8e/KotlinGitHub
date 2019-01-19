package chuprin.serg.khub.user.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import chuprin.serg.khub.R
import chuprin.serg.khub.common.presentation.extensions.*
import chuprin.serg.khub.common.presentation.view.BaseFragment
import chuprin.serg.khub.main.repositories.view.adapter.RepositoryRenderer
import chuprin.serg.khub.user.di.UserInfoComponent
import chuprin.serg.khub.user.model.UserInfoState
import chuprin.serg.khub.user.view.argument.UserInfoArguments
import kotlinx.android.synthetic.main.fragment_user_info.*
import serg.chuprin.adapter.MultiViewAdapter

class UserInfoFragment : BaseFragment() {

    override val layoutResId: Int = R.layout.fragment_user_info

    private val fragmentArgs by args<UserInfoArguments>()
    private val viewModel by viewModel { componentViewModel.component.viewModel }
    private val componentViewModel by componentViewModel {
        UserInfoComponent.get(
            fragmentArgs.login
        )
    }

    private val repositoriesAdapter = MultiViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repositoriesAdapter.registerRenderer(RepositoryRenderer())

        recyclerView.adapter = repositoriesAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        setupToolbar(toolbar) {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.userInfoLiveData.observe(::showUser)
        viewModel.repositoriesLiveData.observe(repositoriesAdapter::setItems)
    }

    private fun showUser(state: UserInfoState) {
        userLoginTextView.text = state.login
        followingCount.text = state.followingCount
        followersCountTextView.text = state.followersCount
        repositoriesCountTextView.text = state.repositoriesCount

        userImageView.load(state.imageUrl, R.drawable.ic_user_placeholder)
    }

}