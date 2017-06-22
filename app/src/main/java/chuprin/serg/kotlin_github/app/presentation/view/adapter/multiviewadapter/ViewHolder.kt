package chuprin.serg.kotlin_github.app.presentation.view.adapter.multiviewadapter

import android.support.v7.widget.RecyclerView
import android.view.View

open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    interface ClickCallback {
        fun onClick(view: View, position: Int)
    }

    interface LongClickCallback {
        fun onLongClick(view: View, position: Int)
    }
}
