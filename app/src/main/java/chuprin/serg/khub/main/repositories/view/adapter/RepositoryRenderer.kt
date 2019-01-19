package chuprin.serg.khub.main.repositories.view.adapter

import chuprin.serg.khub.R
import chuprin.serg.khub.common.data.entity.GithubRepositoryEntity
import chuprin.serg.khub.common.presentation.extensions.visibility
import kotlinx.android.synthetic.main.list_item_repository.view.*
import serg.chuprin.adapter.Click
import serg.chuprin.adapter.LongClick
import serg.chuprin.adapter.ViewHolder
import serg.chuprin.adapter.ViewRenderer

class RepositoryRenderer : ViewRenderer<GithubRepositoryEntity, ViewHolder>() {

    override fun isViewForType(model: Any): Boolean = model is GithubRepositoryEntity

    override fun bindView(holder: ViewHolder, model: GithubRepositoryEntity) {
        with(holder.itemView) {
            name.text = model.name

            description.text = model.description
            when {
                model.language.isEmpty() -> {
                    languageImage.visibility(false); languageText.visibility(false)
                }
                else -> languageText.text = model.language
            }
            stargazersText.text = model.stargazers.toString()
            forksText.text = model.forks.toString()
            isForkText.text = if (model.fork) "(Fork)" else ""
        }
    }

    override fun onVhCreated(
        holder: ViewHolder,
        clickListener: Click?,
        longClickListener: LongClick?
    ) {
        holder.itemView.setOnClickListener {
            clickListener?.onClick(holder.itemView, holder.layoutPosition)
        }
    }

    override fun type(): Int = R.layout.list_item_repository

}