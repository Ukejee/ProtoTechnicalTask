package com.ukejee.prototechnicaltask.ui.home.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.ukejee.prototechnicaltask.common.setImage
import com.ukejee.prototechnicaltask.databinding.ItemVideoBinding
import com.ukejee.prototechnicaltask.ui.home.model.UIVideo

class VideoListViewHolder(private val binding: ItemVideoBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: UIVideo, listener: (() -> Unit)?) {
        binding.duration.text = item.duration
        binding.videoImage.setImage(item.image)
        binding.root.setOnClickListener { listener?.invoke() }
    }
}

