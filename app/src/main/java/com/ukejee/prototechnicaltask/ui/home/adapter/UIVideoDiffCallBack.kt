package com.ukejee.prototechnicaltask.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ukejee.prototechnicaltask.ui.home.model.UIVideo

class UIVideoDiffCallBack(
    private val oldList: List<UIVideo>,
    private val newList: List<UIVideo>
) : DiffUtil.Callback(){

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
