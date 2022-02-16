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
//        viewBinding.rvVideos.addOnScrollListener(object: RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val firstIndex = (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
//                val lastIndex = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
//
//                for (i in list.indices) {
//                    if (i in firstIndex..lastIndex) {
//                        getVideoAdapter()?.playVideo(i)
//                    }
//                }
//            }
//        })

        getVideoAdapter()?.replaceItems(list)
    }

    override fun onPause() {

        super.onPause()
    }

    private fun getVideoAdapter(): VideoAdapter? = viewBinding.rvVideos.adapter as? VideoAdapter

}
