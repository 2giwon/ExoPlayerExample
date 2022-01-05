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
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.MimeTypes

class VideoViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup
): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
), VideoAdapter.StopAction {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ItemVideoBinding.bind(itemView)
    }

    private val context: Context by lazy { binding.root.context }

    private var player: SimpleExoPlayer? = null
    private lateinit var trackSelector: DefaultTrackSelector

    fun onBind(item: String) {
        player = SimpleExoPlayer.Builder(context)
            .build()
            .also { exoPlayer ->
                binding.videoView.player = exoPlayer

                val mediaItem = MediaItem.Builder()
                    .setUri(item)
                    .setMimeType(MimeTypes.APPLICATION_MP4)
                    .build()


                exoPlayer.setMediaItem(mediaItem)
                initPlayer(exoPlayer)
            }
    }

    private fun initPlayer(exoPlayer: SimpleExoPlayer) {
        trackSelector = DefaultTrackSelector(context)
        trackSelector.setParameters(trackSelector.buildUponParameters().setMaxVideoSize(959, 539))

        exoPlayer.playWhenReady = true
        exoPlayer.currentTrackGroups
        exoPlayer.repeatMode = Player.REPEAT_MODE_ONE
        exoPlayer.prepare()
    }

    private fun releasePlayer() {
        binding.videoView.player?.release()
    }

    override fun onReleasePlayer() {
        releasePlayer()
    }
}
