package com.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * 不响应用户滑动的RecyclerView
 */
class NoUserTouchRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private var mUserInputEnabled = true

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        return mUserInputEnabled && super.onInterceptTouchEvent(e)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        return mUserInputEnabled && super.onTouchEvent(e)
    }
}