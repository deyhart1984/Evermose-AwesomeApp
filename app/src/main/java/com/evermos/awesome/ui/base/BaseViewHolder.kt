package com.evermos.awesome.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var currentPosition: Int = 0
        private set

    protected abstract fun clear()

    fun onBind(position: Int) {
        currentPosition = position
        clear()
    }
}
