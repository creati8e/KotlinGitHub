package chuprin.serg.kotlin_github.main.repositories.view

import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.presentation.view.BaseAdapter
import kotlinx.android.synthetic.main.list_item_repository.view.*

class RepositoryAdapter : BaseAdapter<GithubRepositoryEntity>() {

    override fun getLayoutRes(): Int = R.layout.list_item_repository

    override fun bindVh(vh: ViewHolder, data: GithubRepositoryEntity, position: Int) {
        with(vh.itemView) {
            name.text = data.name
            size.text = data.size
        }
    }
}