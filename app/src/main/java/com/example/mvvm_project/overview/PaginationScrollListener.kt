package com.example.mvvm_project.overview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(var layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var prevTotal = 0
    private var loading = true
    private var pageNum = 2

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

        super.onScrollStateChanged(recyclerView, newState)
        if(newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_SETTLING) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if(loading) {
                if(totalItemCount > prevTotal) {
                    loading = false
                    prevTotal = totalItemCount
                }
            }

            var visibleThreshold = 7

            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItemPosition + visibleThreshold)) {
                //end has been reached
                if(pageNum > 5) {
                    loading = false
                }
                else {
                    onLoadMore(pageNum++)
                    loading = true
                }
            }
        }
    }

    protected abstract fun onLoadMore(pageNum : Int)
}