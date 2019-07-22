package com.kevin.demo.module.surfaceview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

/**
 * Create by Kevin-Tu on 2019/7/22.
 */
class PainterView : SurfaceView, SurfaceHolder.Callback, Runnable {

    private lateinit var canvas: Canvas
    private lateinit var paint: Paint
    private lateinit var path: Path
    private var lastX: Float = 0F
    private var lastY: Float = 0F
    private var isDrawing = false

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }


    private fun init() {
        holder.addCallback(this) // 注册SurfaceHolder
        isFocusable = true
        isFocusableInTouchMode = true
        this.keepScreenOn = true // 保持屏幕长亮

        paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        paint.strokeWidth = 10f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND

        path = Path()
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        isDrawing = false
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        isDrawing = true
        Thread(this).start()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var eventX = event.x
        var eventY = event.y
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = eventX
                lastY = eventY
                path.moveTo(lastX, lastY)
            }
            MotionEvent.ACTION_MOVE -> {
                var distanceX = Math.abs(eventX - lastX)
                var distanceY = Math.abs(eventY - lastY)
                if (distanceX > 3 || distanceY > 3) {
                    path.quadTo(lastX, lastY, (lastX + eventX) / 2, (lastY + eventY) / 2)
                    lastX = eventX
                    lastY = eventY
                }
            }
            MotionEvent.ACTION_UP -> {
            }
        }

        return true
    }

    override fun run() {
        while (isDrawing) {
            drawing()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var wSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        var wSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        var hSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        var hSpecSize = MeasureSpec.getSize(heightMeasureSpec)
    }

    private fun drawing() {
        try {
            canvas = holder.lockCanvas()
            canvas.drawColor(Color.WHITE)
            canvas.drawPath(path, paint)
        } finally {

        }
    }

}