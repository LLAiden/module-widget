package com.aiden.modulewidget

import android.graphics.Path
import android.graphics.RectF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.common.widget.PathViewGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Glide.with(this)
            .load("https://img1.baidu.com/it/u=413643897,2296924942&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500")
            .into(findViewById(R.id.mImage))

        findViewById<PathViewGroup>(R.id.mPathViewGroup).apply {
            mPathShape = object : PathViewGroup.PathShape {
                override fun draw(pathViewGroup: PathViewGroup): Path {
                    val rectF =
                        RectF(0F, 0F, pathViewGroup.width.toFloat(), pathViewGroup.height.toFloat())
                    return getAnglePath(rectF, 180F)
                }
            }
        }
    }

    private fun getAnglePath(rectF: RectF, radius: Float): Path {
        val path = Path()
        val floatArray = FloatArray(8) { radius }
        path.addRoundRect(rectF, floatArray, Path.Direction.CCW)
        return path
    }
}