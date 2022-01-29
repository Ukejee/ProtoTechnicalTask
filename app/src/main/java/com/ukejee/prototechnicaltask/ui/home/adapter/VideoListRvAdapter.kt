package com.ukejee.prototechnicaltask.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ukejee.prototechnicaltask.databinding.ItemVideoBinding
import com.ukejee.prototechnicaltask.ui.home.adapter.viewHolder.VideoListViewHolder
import com.ukejee.prototechnicaltask.ui.home.model.UIVideo

class VideoListRvAdapter(): RecyclerView.Adapter<VideoListViewHolder>() {

    private var videoList: MutableList<UIVideo> = mutableListOf()

    var listener: ((item: UIVideo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListViewHolder {
        val binding: ItemVideoBinding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoListViewHolder, position: Int) {
        val item = videoList[position]
        holder.bind(item) { listener?.invoke(item) }
    }

    override fun getItemCount() = videoList.size

    fun replaceList(list: List<UIVideo>) {
        val diffCallBack = UIVideoDiffCallBack(videoList, list)
        DiffUtil.calculateDiff(diffCallBack).dispatchUpdatesTo(this)
        videoList.apply { clear() }.addAll(list)
    }
}
