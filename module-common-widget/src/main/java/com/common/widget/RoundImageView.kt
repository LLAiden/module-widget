package com.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class RoundImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var paint: Paint = Paint()
    private var mRadius = 40F
    private var srcRectF: RectF
    private val porterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    private val srcPath: Path

    override fun onDraw(canvas: Canvas) {
        srcRectF.set(0F, 0F, measuredWidth.toFloat(), measuredHeight.toFloat())
        val saveLayer = canvas.saveLayer(srcRectF, paint)
        if (drawable == null) {
            return
        }
        super.onDraw(canvas)
        val anglePath = getAnglePath(srcRectF, mRadius)
        srcPath.reset()
        srcPath.addRect(srcRectF, Path.Direction.CCW)
        srcPath.op(anglePath, Path.Op.DIFFERENCE)
        paint.xfermode = porterDuffXfermode
        canvas.drawPath(srcPath, paint)
        paint.xfermode = null
        canvas.restoreToCount(saveLayer)
    }

    fun setRadius(radius: Float) {
        mRadius = radius
    }

    fun setRadiusInvalidate(radius: Float) {
        mRadius = radius
        postInvalidate()
    }

    init {
        paint.isAntiAlias = true
        paint.isDither = true
        srcRectF = RectF()
        srcPath = Path()
    }

    private fun getAnglePath(rectF: RectF, radius: Float): Path {
        val path = Path()
        val floatArray = FloatArray(8) { radius }
        path.addRoundRect(rectF, floatArray, Path.Direction.CCW)
        return path
    }
}