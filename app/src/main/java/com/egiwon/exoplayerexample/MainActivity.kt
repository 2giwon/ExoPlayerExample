package com.egiwon.exoplayerexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.exoplayerexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val list = mutableListOf<String>()
        for (i in 0 until 10) {
            list.add(resources.getString(R.string.streaming_url))
        }

        viewBinding.rvVideos.adapter = VideoAdapter(R.layout.item_video)
        layoutManager = viewBinding.rvVideos.layoutManager as LinearLayoutManager

        viewBinding.rvVideos.setHasFixedSize(true)
        viewBinding.rvVideos.addItemDecoration(VideoItemDecoration())

        getVideoAdapter()?.replaceItems(list)
    }

    override fun onPause() {
        getVideoAdapter()?.releaseAllVideo()
        super.onPause()
    }

    private fun getVideoAdapter(): VideoAdapter? = viewBinding.rvVideos.adapter as? VideoAdapter

}
