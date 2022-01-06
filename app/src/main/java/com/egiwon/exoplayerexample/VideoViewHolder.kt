package com.egiwon.exoplayerexample

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.exoplayerexample.databinding.ItemVideoBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.MimeTypes

class VideoViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup
): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
), VideoAdapter.VideoAdapterAction {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ItemVideoBinding.bind(itemView)
    }

    private val context: Context by lazy { binding.root.context }

    private var player: SimpleExoPlayer? = null

    private var url: String = ""

    fun onBind(item: String) {
        url = item
        initPlayer()
    }

    private fun initPlayer() {

        player = SimpleExoPlayer.Builder(context)
            .build()
            .also { exoPlayer ->
                binding.videoView.player = exoPlayer

                val mediaItem = MediaItem.Builder()
                    .setUri(url)
                    .setMimeType(MimeTypes.APPLICATION_MP4)
                    .build()

                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.currentTrackGroups
                exoPlayer.repeatMode = Player.REPEAT_MODE_ONE
                exoPlayer.playWhenReady = true
                exoPlayer.prepare()
            }
    }

    private fun releasePlayer() {
        player?.release()
        player = null
    }

    override fun onReleasePlayer() {
        releasePlayer()
    }

    override fun onPlay() {
        if (player == null) {
            initPlayer()
        }
    }

    fun onStop() {
        releasePlayer()
    }
}
