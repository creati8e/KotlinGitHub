package chuprin.serg.kotlin_github.main.users.view.adapter

import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.app.data.entity.GithubUserEntity
import chuprin.serg.kotlin_github.app.presentation.view.adapter.multiviewadapter.ViewHolder
import chuprin.serg.kotlin_github.app.presentation.view.adapter.multiviewadapter.ViewRenderer
import chuprin.serg.kotlin_github.app.presentation.view.utils.load
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserRenderer : ViewRenderer<GithubUserEntity, ViewHolder>() {

    override fun type(): Int = R.layout.list_item_user

    override fun isViewForType(model: Any): Boolean = model is GithubUserEntity

    override fun bindView(holder: ViewHolder, model: GithubUserEntity) {
        holder.itemView.textView.text = model.login
        holder.itemView.imageView.load(model.avatarUrl, R.drawable.ic_user_placeholder)
    }

    override fun onVhCreated(holder: ViewHolder, clickListener: ViewHolder.ClickCallback?) {
        holder.itemView.setOnClickListener {
            clickListener?.onClick(it, holder.layoutPosition)
        }
    }
}