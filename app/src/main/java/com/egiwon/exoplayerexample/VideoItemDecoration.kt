package com.egiwon.exoplayerexample

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VideoItemDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = view.resources.getDimensionPixelOffset(R.dimen.video_item_decoration_top)
        outRect.left = view.resources.getDimensionPixelOffset(R.dimen.video_item_decoration_start)
    }
}
