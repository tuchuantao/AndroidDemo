package com.kevin.demo.module.notification

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityNotificationBinding

/**
 * Create by Kevin-Tu on 2019/12/20.
 */
class NotificationActivity : BaseActivity<ActivityNotificationBinding>() {

    private val manager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
    private var notificationId: Int = 1

    override fun initBinding(): ActivityNotificationBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.activity_notification,
            null,
            false
        )
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.btnNormal.setOnClickListener {
            showNotification()
        }
        binding.btnGetNotification.setOnClickListener {
            getNotification()
        }
    }

    private fun getNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var notifications = manager.activeNotifications
            notifications?.let {
                notifications.forEach {
                    it
                    Log.v("kevin", "it: $it")
                }
            }

            if (notifications?.isNotEmpty() == true) {
                var status = notifications[notifications.size - 1]
                Log.v("kevin", "status: $status")
                manager.notify(status.id, status.notification)
            }
        } else {
        }
    }

    private fun showNotification() {
        var notification = NotificationCompat.Builder(this, "AndroidDemo")
            .setContentTitle("通知标题 ID=$notificationId")
            .setContentText("通知内容")
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.icon_rounded)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.icon_rounded))
            .setAutoCancel(true) // 点击后自动消失
            //.setContentIntent(Intent())   // PendingIntent进行通知点击跳转功能
            //.setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg"))) // 设置通知提示音
            //.setVibrate() // 设置振动， 需要添加权限  <uses-permission android:name="android.permission.VIBRATE"/>
            //.setLights(Color.GREEN,1000,1000) // 设置前置LED灯进行闪烁， 第一个为颜色值  第二个为亮的时长  第三个为暗的时长
            //.setDefaults(NotificationCompat.DEFAULT_ALL)  // 使用默认效果， 会根据手机当前环境播放铃声， 是否振动
            // 长文本
            //.setStyle(new NotificationCompat.BigTextStyle().bigText("这是一段很长的文字很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长"))
            // 通知显示大图
            //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // 通知的重要程度
            /**
            PRIORITY_DEFAULT ： 表示默认重要程度，和不设置效果一样
            PRIORITY_MIN     ： 表示最低的重要程度。系统只会在用户下拉状态栏的时候才会显示
            PRIORITY_LOW     ： 表示较低的重要性，系统会将这类通知缩小，或者改变显示的顺序，将排在更重要的通知之后。
            PRIORITY_HIGH    ： 表示较高的重要程度，系统可能会将这类通知方法，或改变显示顺序，比较靠前
            PRIORITY_MAX     ： 最重要的程度， 会弹出一个单独消息框，让用户做出相应
             */
            .build()

        manager.notify(notificationId, notification)
        notificationId++
    }
}