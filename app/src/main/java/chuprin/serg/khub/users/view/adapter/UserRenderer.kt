package chuprin.serg.khub.users.view.adapter

import chuprin.serg.khub.R
import chuprin.serg.khub.common.data.entity.GithubUserEntity
import chuprin.serg.khub.common.presentation.extensions.load
import kotlinx.android.synthetic.main.list_item_user.view.*
import serg.chuprin.adapter.Click
import serg.chuprin.adapter.LongClick
import serg.chuprin.adapter.ViewHolder
import serg.chuprin.adapter.ViewRenderer

class UserRenderer : ViewRenderer<GithubUserEntity, ViewHolder>() {

    override fun type(): Int = R.layout.list_item_user

    override fun isViewForType(model: Any): Boolean = model is GithubUserEntity

    override fun bindView(holder: ViewHolder, model: GithubUserEntity) {
        holder.itemView.textView.text = model.login
        holder.itemView.imageView.load(model.avatarUrl, R.drawable.ic_user_placeholder)
    }

    override fun onVhCreated(
        holder: ViewHolder,
        clickListener: Click?,
        longClickListener: LongClick?
    ) {
        holder.itemView.setOnClickListener {
            clickListener?.onClick(it, holder.layoutPosition)
        }
    }

}