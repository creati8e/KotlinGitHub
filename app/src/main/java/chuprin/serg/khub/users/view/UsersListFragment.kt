package chuprin.serg.khub.users.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import chuprin.serg.khub.R
import chuprin.serg.khub.common.data.entity.GithubUserEntity
import chuprin.serg.khub.common.presentation.extensions.componentViewModel
import chuprin.serg.khub.common.presentation.extensions.observeScrollEvents
import chuprin.serg.khub.common.presentation.extensions.toFragmentArgs
import chuprin.serg.khub.common.presentation.extensions.viewModel
import chuprin.serg.khub.common.presentation.view.BaseFragment
import chuprin.serg.khub.common.presentation.view.adapter.pagination.NetworkErrorModel
import chuprin.serg.khub.common.presentation.view.adapter.pagination.NetworkErrorRenderer
import chuprin.serg.khub.common.presentation.view.adapter.pagination.PaginationAdapter
import chuprin.serg.khub.common.presentation.view.adapter.pagination.ProgressRenderer
import chuprin.serg.khub.user.view.argument.UserInfoArguments
import chuprin.serg.khub.users.di.UsersComponent
import chuprin.serg.khub.users.view.adapter.UserRenderer
import kotlinx.android.synthetic.main.fragment_list.*

class UsersListFragment : BaseFragment() {

    override val layoutResId: Int = R.layout.fragment_list

    private val viewModel by viewModel { componentViewModel.component.viewModel }
    private val componentViewModel by componentViewModel { UsersComponent.get() }

    private val adapter: PaginationAdapter = PaginationAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter.registerRenderer(UserRenderer())
        adapter.registerRenderer(ProgressRenderer())
        adapter.registerRenderer(NetworkErrorRenderer())
        adapter.clickListener = { model, _, _ -> onAdapterClick(model) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        paginate()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.listLiveData.observe { adapter.setItems(it) }
    }

    private fun onAdapterClick(model: Any) {
        when (model) {
            is NetworkErrorModel -> paginate()
            is GithubUserEntity -> {
                navController.navigate(
                    R.id.userInfoFragment,
                    UserInfoArguments(model.login).toFragmentArgs()
                )
            }
        }
    }

    private fun paginate() = viewModel.paginate(recyclerView.observeScrollEvents())

}

