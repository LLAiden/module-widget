package com.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout

class PathViewGroup @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mTmpPathShape = object : PathShape {
        override fun draw(pathViewGroup: PathViewGroup): Path {
            val mPath = Path()
            val centerX = pathViewGroup.width / 2
            val centerY = pathViewGroup.height / 2
            val radius = pathViewGroup.width / 2
            mPath.reset()
            mPath.addCircle(
                centerX.toFloat(),
                centerY.toFloat(),
                radius.toFloat(),
                Path.Direction.CW
            )
            return mPath
        }
    }

    var mPathShape = mTmpPathShape
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

    override fun dispatchDraw(canvas: Canvas) {
        val saveCount = canvas.save()
        val drawPath = mPathShape.draw(this)
        if (!drawPath.isEmpty) {
            canvas.clipPath(drawPath)
        }
        super.dispatchDraw(canvas)
        canvas.restoreToCount(saveCount)
    }

    interface PathShape {
        fun draw(pathViewGroup: PathViewGroup): Path
    }
}