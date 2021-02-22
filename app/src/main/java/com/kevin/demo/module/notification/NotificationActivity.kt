package com.kevin.demo.module.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
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

    override fun getLayoutResId(): Int {
        return R.layout.activity_notification
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.btnNormal.setOnClickListener {
            showNotification()
        }
        binding.btnGetNotification.setOnClickListener {
//            getNotification()
            showNotification2()
        }
    }

    private fun getNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var notifications = manager.activeNotifications
            notifications?.let {
                notifications.forEach {
                    it
                    Log.v("kevintu", "it: $it")
                    var notify = it.notification
                    var view = notify.contentView
                }
            }

            if (notifications?.isNotEmpty() == true) {
                var status = notifications[notifications.size - 1]
                Log.v("kevintu", "status: $status")
                Log.v("kevintu", "status.id: ${status.id}")
                manager.notify(status.id, status.notification)
            }
        } else {
        }
        var cla = manager::class.java
        /*try {
            var methods = cla.getDeclaredMethod("getService")
            if (methods != null) {
                var service =  methods.invoke(manager)
                var serCla = service::class.java
                var serMethods = serCla.declaredMethods
                if (serMethods != null) {
                    serMethods.forEach {
                        it.name
                    }
                }
                var serFields = serCla.declaredFields
                if (serFields != null) {
                    serFields.forEach {
                        it.name
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }*/

        /*
        *
        try {
                    Method method = cla.getDeclaredMethod("getService");
                    if (method != null) {
                        Object service =  method.invoke(manager);
                        Class serCla = service.getClass();
                        Method serMethod = serCla.getDeclaredMethod("getActiveNotifications", String.class);
                        if (serMethod != null) {
                            StatusBarNotification[] notifications = (StatusBarNotification[]) serMethod.invoke(service, "com.xiaomi.xmsf");
                            if (notifications != null) {
                                for (int i = 0; i < notifications.length; i++) {
                                    int id = notifications[i].getId();
                                    String tag = notifications[i].getTag();
                                }
                            }
                            StatusBarNotification[] notifications2 = (StatusBarNotification[]) serMethod.invoke(service, "com.kevin.demo");
                            if (notifications2 != null) {
                                for (int i = 0; i < notifications2.length; i++) {
                                    int id = notifications2[i].getId();
                                    String tag = notifications2[i].getTag();
                                }
                            }
                            StatusBarNotification[] notifications3 = (StatusBarNotification[]) serMethod.invoke(service, "com.miui.video");
                            if (notifications3 != null) {
                                for (int i = 0; i < notifications3.length; i++) {
                                    int id = notifications3[i].getId();
                                    String tag = notifications3[i].getTag();
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        * */

    }

    private fun showNotification() {
        var notification = NotificationCompat.Builder(this, "AndroidDemo")
            .setContentTitle("通知标题 ID=$notificationId")
            .setContentText("通知内容")
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.icon_rounded) // 必须添加（Android 8.0）
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

        // android9.0 添加NotificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel = NotificationChannel(
                "AndroidDemo",
                "会话消息",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel);
        }
        manager.notify(notificationId, notification)
        notificationId++
    }

    private fun showNotification2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel = NotificationChannel("AndroidDemo2", "AndroidDemo", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        var remoteViews = RemoteViews(getPackageName(), R.layout.vpn_notification_layout);
        remoteViews.setImageViewResource(R.id.logo_img, R.mipmap.ic_launcher)
        remoteViews.setTextViewText(R.id.notify_title, "title")
        remoteViews.setTextViewText(R.id.content_speed, "desc")

        var notification = NotificationCompat.Builder(this, "AndroidDemo2")
            .setContent(remoteViews)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOngoing(true)
            .setWhen(System.currentTimeMillis())
            .build()
        manager.notify(notificationId, notification)
        notificationId++
    }
}