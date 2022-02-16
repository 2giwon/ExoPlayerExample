package com.egiwon.exoplayerexample

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(
    @LayoutRes private val layoutResId: Int
): RecyclerView.Adapter<VideoViewHolder>() {

    private val list = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(layoutResId, parent)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun onViewDetachedFromWindow(holder: VideoViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onReleasePlayer()
    }

    override fun onViewAttachedToWindow(holder: VideoViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onPlay()
    }

    override fun onViewRecycled(holder: VideoViewHolder) {
        super.onViewRecycled(holder)
        holder.onReleasePlayer()
    }

    @Suppress("NotifyDataSetChanged")
    fun replaceItems(items: List<String>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}
