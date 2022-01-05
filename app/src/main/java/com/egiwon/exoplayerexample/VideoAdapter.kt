package com.egiwon.exoplayerexample

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(
    @LayoutRes private val layoutResId: Int
): RecyclerView.Adapter<VideoViewHolder>() {

    interface StopAction {
        fun onReleasePlayer()
    }

    private val list = mutableListOf<String>()

    private val stopActionList = mutableListOf<StopAction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val holder = VideoViewHolder(layoutResId, parent)
        stopActionList.add(holder)
        return holder
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun releaseVideo() {
        stopActionList.forEach {
            it.onReleasePlayer()
        }
    }

    fun replaceItems(items: List<String>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}
