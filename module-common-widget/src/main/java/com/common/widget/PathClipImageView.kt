package com.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet

class PathClipImageView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    var mPathShape: PathShape? = null
        set(value) {
            field = value
            invalidate()
        }

    init {
        mPaint.color = Color.TRANSPARENT
        mPaint.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = measuredWidth.coerceAtMost(measuredHeight)
        setMeasuredDimension(size, size)
    }


    override fun draw(canvas: Canvas) {
        val saveCount = canvas.save()
        mPathShape?.clip(canvas, this)
        super.draw(canvas)
        canvas.restoreToCount(saveCount)
    }

    interface PathShape {
        fun clip(canvas: Canvas, pathView: PathClipImageView)
    }
}