package chuprin.serg.kotlin_github.main.users.view

import chuprin.serg.kotlin_github.KotApplication
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.main.MainComponent
import chuprin.serg.kotlin_github.main.MainModule
import chuprin.serg.kotlin_github.main.users.presenter.UsersListPresenter
import chuprin.serg.kotlin_github.main.view.ListFragment
import chuprin.serg.kotlin_github.user.view.UserActivity
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class UsersListFragment : ListFragment<GithubUserEntity, UsersAdapter>(), UsersListView {

    @Inject lateinit var presenter: UsersListPresenter

    override fun createAdapter(): UsersAdapter = UsersAdapter()

    override fun onItemClicked(model: GithubUserEntity, pos: Int) {
        activity.startActivity<UserActivity>("login" to model.login)
    }

    override fun createComponent() = KotApplication.component.mainComponent(MainModule(arguments))

    override fun componentClass(): Class<*> = MainComponent::class.java
}
