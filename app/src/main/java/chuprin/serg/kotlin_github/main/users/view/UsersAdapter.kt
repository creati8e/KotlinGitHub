package chuprin.serg.kotlin_github.main.users.view

import android.view.ViewGroup
import chuprin.serg.kotlin_github.R
import chuprin.serg.kotlin_github.app.data.entity.UserEntity
import chuprin.serg.kotlin_github.app.presentation.view.BaseAdapter
import chuprin.serg.kotlin_github.app.presentation.view.setListener
import chuprin.serg.kotlin_github.app.presentation.view.utils.load
import kotlinx.android.synthetic.main.list_item_user.view.*

class UsersAdapter : BaseAdapter<UserEntity>() {

    override fun bindVh(vh: ViewHolder, data: UserEntity, position: Int) {
        vh.itemView.textView.text = data.login
        vh.itemView.imageView.load(data.avatarUrl, R.drawable.ic_user_placeholder)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val holder = super.onCreateViewHolder(parent, viewType)
        return holder.setListener(holder.itemView, this)
    }

    override fun getLayoutRes(): Int = R.layout.list_item_user

}