package chuprin.serg.kotlin_github.main.repos.view

import android.os.Bundle
import chuprin.serg.kotlin_github.app.data.entity.RepoEntity
import chuprin.serg.kotlin_github.app.di.MyApplication
import chuprin.serg.kotlin_github.main.DaggerMainComponent
import chuprin.serg.kotlin_github.main.MainModule
import chuprin.serg.kotlin_github.main.repos.presenter.ReposPresenter
import chuprin.serg.kotlin_github.main.view.ListFragment
import chuprin.serg.kotlin_github.user.view.UserActivity
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class ReposFragment : ListFragment<RepoEntity, RepositoryAdapter>(), ReposView {
    override fun createComponent(savedState: Bundle?): Any {
        return DaggerMainComponent.builder()
                .appComponent(MyApplication.component)
                .mainModule(MainModule(savedState))
                .build()
    }

    @Inject internal lateinit var presenter: ReposPresenter

    companion object {
        fun newInstance(): ReposFragment = ReposFragment()
    }

    override fun createAdapter(): RepositoryAdapter = RepositoryAdapter()

    override fun onItemClicked(model: RepoEntity, pos: Int) {
        activity.startActivity<UserActivity>("login" to model.name)
    }

}