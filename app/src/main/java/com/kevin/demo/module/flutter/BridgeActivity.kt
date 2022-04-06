package com.kevin.demo.module.flutter

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

/**
 * Created by tuchuantao on 2022/4/6
 * Desc:
 */
class BridgeActivity : FlutterActivity() {
  private val CHANNEL = "com.kevin/battery"

  companion object {
    fun withNewEngine(): NewEngineIntentBuilder {
      return NewEngineIntentBuilder(BridgeActivity::class.java)
    }

    fun withCachedEngine(cachedEngineId: String): CachedEngineIntentBuilder {
      return CachedEngineIntentBuilder(BridgeActivity::class.java, cachedEngineId)
    }
  }

  override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
    GeneratedPluginRegistrant.registerWith(flutterEngine)

//    Flutter定义了三种不同类型的Platform Channel用于Flutter与Host App平台进行通信，它们分别是
//    BasicMessageChannel：用于传递字符串和半结构化的信息，可以双向的请求数据。
//    MethodChannel：用于传递方法调用（method invocation，即Flutter端可以调用Platform端的方法并通过Result接口回调结果数据。
//    EventChannel: 用于数据流（event streams）的通信，即Flutter端监听Platform端的实时消息，一旦Platform端产生了数据，立即回调到Flutter端。

    // 1.创建MethodChannel对象
    val methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)

    // 2.添加调用方法的回调
    methodChannel.setMethodCallHandler {
      // Note: this method is invoked on the main thread.
        call, result ->
      // 2.1.如果调用的方法是getBatteryInfo,那么正常执行
      if (call.method == "getBatteryInfo") {
        // 2.1.1.调用另外一个自定义方法回去电量信息
        val batteryLevel = getBatteryLevel()

        // 2.1.2. 判断是否正常获取到
        if (batteryLevel != -1) {
          // 获取到返回结果
          result.success(batteryLevel)
        } else {
          // 获取不到抛出异常
          result.error("UNAVAILABLE", "Battery level not available.", null)
        }
      } else {
        // 2.2.如果调用的方法是getBatteryInfo,那么正常执行
        result.notImplemented()
      }
    }
  }

  private fun getBatteryLevel(): Int {
    val batteryLevel: Int
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
      batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    } else {
      val intent = ContextWrapper(applicationContext).registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
      batteryLevel = intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 / intent.getIntExtra(
        BatteryManager.EXTRA_SCALE, -1)
    }

    return batteryLevel
  }
}