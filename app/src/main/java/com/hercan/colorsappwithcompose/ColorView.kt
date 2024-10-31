package com.hercan.colorsappwithcompose

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class ColorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        strokeWidth = 20f
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
    }

    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        path.reset()

        val width = width.toFloat()
        val height = height.toFloat()
        val radius = height / 2
        path.apply {
            moveTo(width, 0f)
            arcTo(0f, 0f, 2 * radius, height, 90f, 180f, false)
            moveTo(width, 0f)
            lineTo(width, height)
            lineTo(radius, height)
            close()
        }
        canvas.drawPath(path, paint)
    }

    fun setColor(hexColor: String) {
        try {
            paint.color = Color.parseColor(hexColor)
            invalidate()
        } catch (e: IllegalArgumentException) {
            paint.color = Color.TRANSPARENT
        }
    }
}