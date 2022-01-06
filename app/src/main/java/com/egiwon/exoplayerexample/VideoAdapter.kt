package com.egiwon.exoplayerexample

import android.util.Log
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(
    @LayoutRes private val layoutResId: Int
): RecyclerView.Adapter<VideoViewHolder>() {

    interface VideoAdapterAction {
        fun onReleasePlayer()
        fun onPlay()
    }

    private val list = mutableListOf<String>()

    private val videoActionList = mutableListOf<VideoAdapterAction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val holder = VideoViewHolder(layoutResId, parent)
        videoActionList.add(holder)
        return holder
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun onViewRecycled(holder: VideoViewHolder) {
        super.onViewRecycled(holder)
        holder.onStop()
    }

    fun releaseVideo() {
        videoActionList.forEach {
            it.onReleasePlayer()
        }
    }

    fun playVideo(index: Int) {
        runCatching { videoActionList[index] }
            .onSuccess(VideoAdapterAction::onPlay)
    }

    fun replaceItems(items: List<String>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}
