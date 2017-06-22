package chuprin.serg.kotlin_github.main.users.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import chuprin.serg.kotlin_github.KotApplication
import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.presentation.view.adapter.pagination.NetworkErrorModel
import chuprin.serg.kotlin_github.app.presentation.view.adapter.pagination.NetworkErrorRenderer
import chuprin.serg.kotlin_github.app.presentation.view.adapter.pagination.PaginationAdapter
import chuprin.serg.kotlin_github.app.presentation.view.adapter.pagination.ProgressRenderer
import chuprin.serg.kotlin_github.app.presentation.view.utils.paginate
import chuprin.serg.kotlin_github.app.presentation.view.utils.visibility
import chuprin.serg.kotlin_github.main.users.UsersModule
import chuprin.serg.kotlin_github.main.users.presenter.UsersListPresenter
import chuprin.serg.kotlin_github.main.users.view.adapter.UserRenderer
import chuprin.serg.kotlin_github.user.UserComponent
import chuprin.serg.kotlin_github.user.view.UserActivity
import kotlinx.android.synthetic.main.fragment_list.*
import mvp_core.view.MvpFragment
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

class UsersListFragment : MvpFragment<UsersListPresenter>(), UsersListView {

    @Inject lateinit var presenter: UsersListPresenter
    private val adapter: PaginationAdapter = PaginationAdapter()

    override fun createComponent() = KotApplication.component.usersComponent(UsersModule())

    override fun componentClass(): Class<*> = UserComponent::class.java

    override fun onViewCreated(view: View?, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
        progress.visibility(false)
        adapter.registerRenderer(ProgressRenderer())
        adapter.registerRenderer(UserRenderer())
        adapter.registerRenderer(NetworkErrorRenderer())
        adapter.clickListener = { model, _, _ -> onAdapterClick(model) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        paginate()
    }

    override fun paginationProgress(visible: Boolean) = adapter.setLoadingMoreVisibility(visible)

    override fun showNetworkError(visible: Boolean) = adapter.setNetworkErrorVisibility(visible)

    override fun showData(data: List<GithubUserEntity>) = adapter.setItems(data)

    override fun addData(list: List<GithubUserEntity>) = adapter.addItems(list)

    override fun showProgress(visible: Boolean) = progress.visibility(visible)

    override fun getLayoutRes(): Int = R.layout.fragment_list

    override fun showMessage(message: Int) = activity.toast(message)

    private fun onAdapterClick(model: Any) {
        when (model) {
            is NetworkErrorModel -> paginate()
            is GithubUserEntity -> activity.startActivity<UserActivity>("login" to model.login)
        }
    }

    private fun paginate() = presenter.paginate(recyclerView.paginate())
}

