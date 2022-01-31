package com.ukejee.prototechnicaltask.ui.home.adapter.viewHolder

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ukejee.prototechnicaltask.common.setImage
import com.ukejee.prototechnicaltask.customView.HighlighterDialog
import com.ukejee.prototechnicaltask.databinding.ItemVideoBinding
import com.ukejee.prototechnicaltask.ui.home.model.UIVideo

class VideoListViewHolder(private val binding: ItemVideoBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: UIVideo, activity: Activity, listener: (() -> Unit)?) {
        binding.duration.text = item.duration
        binding.videoImage.setImage(item.image)
        binding.root.setOnClickListener { listener?.invoke() }
        binding.root.setOnLongClickListener(object: View.OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {
                setupHighlighterDialog(activity, item)
                return true
            }

        })
    }

    private fun setupHighlighterDialog(activity: Activity, item: UIVideo) {
        val dialog = HighlighterDialog(activity)
        //dialog.setPeekThroughView(binding.root)
        dialog.setHighlightedViewDescription(item.description)
        dialog.setImageView(item.image)
        dialog.setCancelable(true)
        dialog.show()

    }
}

