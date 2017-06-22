package chuprin.serg.kotlin_github.main.repositories.view

import android.view.ViewGroup
import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.app.data.entity.GithubRepositoryEntity
import chuprin.serg.kotlin_github.app.presentation.view.adapter.BaseAdapter
import chuprin.serg.kotlin_github.app.presentation.view.adapter.setListener
import chuprin.serg.kotlin_github.app.presentation.view.utils.visibility
import kotlinx.android.synthetic.main.list_item_repository.view.*

class RepositoryAdapter : BaseAdapter<GithubRepositoryEntity>() {

    override fun getLayoutRes(): Int = R.layout.list_item_repository

    override fun bindVh(vh: ViewHolder, data: GithubRepositoryEntity, position: Int) {
        with(vh.itemView) {
            name.text = data.name

            description.text = data.description
            when {
                data.language.isEmpty() -> {
                    languageImage.visibility(false); languageText.visibility(false)
                }
                else -> languageText.text = data.language
            }
            stargazersText.text = data.stargazers.toString()
            forksText.text = data.forks.toString()
            isForkText.text = if (data.fork) "(Fork)" else ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val holder = super.onCreateViewHolder(parent, viewType)
        return holder.setListener(holder.itemView, this)
    }

}