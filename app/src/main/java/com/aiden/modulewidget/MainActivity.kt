package com.aiden.modulewidget

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.common.widget.PathClipImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Glide.with(this)
//            .load("https://img1.baidu.com/it/u=413643897,2296924942&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500")
//            .into(findViewById(R.id.mImage))

        findViewById<PathClipImageView>(R.id.mImage).apply {
            mPathShape = object : PathClipImageView.PathShape {

                @RequiresApi(Build.VERSION_CODES.O)
                override fun clip(canvas: Canvas, pathView: PathClipImageView) {
                    canvas.clipOutRect(100F, 100F, pathView.width.toFloat() - 100, pathView.height.toFloat()- 100)
                }
            }
        }
    }

    private fun getAnglePath(rectF: RectF, radius: Float): Path {
        val path = Path()
        val floatArray = floatArrayOf(radius, radius, 0F, 0F, radius, radius, 0F, 0F, 0F, 0F)
        path.addRoundRect(rectF, floatArray, Path.Direction.CCW)
        return path
    }
}