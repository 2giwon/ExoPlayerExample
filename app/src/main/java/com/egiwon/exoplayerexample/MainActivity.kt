package com.egiwon.exoplayerexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.exoplayerexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.rvVideos.adapter = VideoAdapter(R.layout.item_video)
        viewBinding.rvVideos.setHasFixedSize(true)

        val list = mutableListOf<String>()
        for (i in 0 until 10) {
            list.add(resources.getString(R.string.streaming_url))
        }

        getVideoAdapter()?.replaceItems(list)
    }

    override fun onStop() {
        super.onStop()
        getVideoAdapter()?.releaseVideo()
    }

    private fun getVideoAdapter(): VideoAdapter? = viewBinding.rvVideos.adapter as? VideoAdapter

}
