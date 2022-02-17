package com.dinhtai.vpstest.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper


class ItemTouchHelperCallback(mListenner: ItemTouchListenner) :
    ItemTouchHelper.Callback() {
    private val mListenner: ItemTouchListenner
    override fun isLongPressDragEnabled(): Boolean {
        return super.isLongPressDragEnabled()
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return super.isItemViewSwipeEnabled()
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val swipeFlag = 0
        return makeMovementFlags(dragFlags, swipeFlag)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        mListenner.onMove(viewHolder.adapterPosition, target.adapterPosition)
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        mListenner.swipe(viewHolder.adapterPosition, direction)
    }

    init {
        this.mListenner = mListenner
    }
}

interface ItemTouchListenner {
    fun onMove(oldPosition: Int, newPosition: Int)
    fun swipe(position: Int, direction: Int)
}