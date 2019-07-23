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
import kotlin.concurrent.thread

/**
 * Create by Kevin-Tu on 2019/7/22.
 */
class PainterView : SurfaceView, SurfaceHolder.Callback, Runnable {

    private lateinit var canvas: Canvas
    private lateinit var paint: Paint
    private lateinit var path: Path
    private var lastX: Float = 0F
    private var lastY: Float = 0F
    @Volatile private var isDrawing = false
    @Volatile private var needDrawing = false

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

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        isDrawing = false
        holder.removeCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        // 绘制白色背景
        drawing()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var eventX = event.x
        var eventY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isDrawing = true
                thread(start = true) {

                }
                Thread(this).start()

                lastX = eventX
                lastY = eventY
                path.moveTo(lastX, lastY)
                needDrawing = true
            }
            MotionEvent.ACTION_MOVE -> {
                var distanceX = Math.abs(eventX - lastX)
                var distanceY = Math.abs(eventY - lastY)
                if (distanceX > 3 || distanceY > 3) {
                    path.quadTo(lastX, lastY, (lastX + eventX) / 2, (lastY + eventY) / 2)
                    lastX = eventX
                    lastY = eventY
                }
                needDrawing = true
            }
            MotionEvent.ACTION_UP -> {
                isDrawing = false
            }
        }

        return true
    }

    override fun run() {
        while (isDrawing) {
            if (needDrawing) { // 避免无效绘制，绘制相同的内容
                drawing()
            }
        }
    }

    private fun drawing() {
        try {
            canvas = holder.lockCanvas() // lockCanvas()获取到的Canvas对象还是上次的Canvas对象，并不是一个新的对象。之前的绘图都将被保留
            canvas.drawColor(Color.WHITE)
            canvas.drawPath(path, paint)
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas) // 进行画布内容的提交
            }
        }
    }
}