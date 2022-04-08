package com.kevin.demo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityMainBinding
import com.kevin.demo.hook.HookHelper
import com.kevin.demo.hook.TargetActivity
import com.kevin.demo.module.affinity.StandardActivity
import com.kevin.demo.module.aidl.AidlActivity
import com.kevin.demo.module.animationdra.DrawableAnimActivity
import com.kevin.demo.module.banner.BannerActivity
import com.kevin.demo.module.bitmap.BitmapActivity
import com.kevin.demo.module.constraintlayout.ConstraintLayoutActivity
import com.kevin.demo.module.coordinatorlayout.CoordinatorLayoutActivity
import com.kevin.demo.module.customview.CustomActivity
import com.kevin.demo.module.databinding.DataBindingActivity
import com.kevin.demo.module.datastore.DataStoreActivity
import com.kevin.demo.module.db.DBActivity
import com.kevin.demo.module.fadingedge.FadingEdgeActivity
import com.kevin.demo.module.flutter.BridgeActivity
import com.kevin.demo.module.fps.FpsMonitorActivity
import com.kevin.demo.module.handler.HandlerActivity
import com.kevin.demo.module.inflater.LayoutInflaterActivity
import com.kevin.demo.module.lottie.LottieActivity
import com.kevin.demo.module.mmkv.MMKVActivity
import com.kevin.demo.module.newactivityresult.MyActivityResultContract
import com.kevin.demo.module.notification.NotificationActivity
import com.kevin.demo.module.okio.OkioActivity
import com.kevin.demo.module.parcelable.ParcelableActivity
import com.kevin.demo.module.screenshoot.ScreenShootActivity
import com.kevin.demo.module.service.ServiceActivity
import com.kevin.demo.module.surfaceview.SurfaceViewActivity
import com.kevin.demo.module.tablayout.TabLayoutActivity
import com.kevin.demo.module.tint.DrawableTintActivity
import com.kevin.demo.module.touch.TouchEventActivity
import com.kevin.demo.module.view.ViewMeasureActivity
import com.kevin.demo.module.view.ViewTraversalActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

  override fun getLayoutResId(): Int {
    return R.layout.activity_main
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    updateFontScale(1.3f)
    super.onCreate(savedInstanceState)
    HookHelper.startHook();
  }

  override fun initView(savedInstanceState: Bundle?) {
    binding.btnHookStart.setOnClickListener {
      // TargetActivity没有在清单文件中配置
      var intent = Intent(this, TargetActivity::class.java)
      startActivity(intent)
    }

    binding.btnFlutter.setOnClickListener {
//      var intent = FlutterActivity.createDefaultIntent(this)
//      var intent = FlutterActivity.withNewEngine()
//        // 背景颜色，透明 or 不透明
//        .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
//        // 指定跳转的路由 home 页面
//        .initialRoute("home")
//        .build(this)

      var intent = BridgeActivity.withNewEngine().build(this)
      intent.setClass(this, BridgeActivity::class.java)
      startActivity(intent)
    }

    binding.btnDatastore.setOnClickListener {
      var intent = Intent(this, DataStoreActivity::class.java)
      startActivity(intent)
    }
    binding.btnMmkv.setOnClickListener {
      var intent = Intent(this, MMKVActivity::class.java)
      startActivity(intent)
    }

    binding.btnScreenShoot.setOnClickListener {
      var intent = Intent(this, ScreenShootActivity::class.java)
      startActivity(intent)
    }

    binding.btnParcel.setOnClickListener {
      var intent = Intent(this, ParcelableActivity::class.java)
      startActivity(intent)
    }

    binding.btnBitmap.setOnClickListener {
      var intent = Intent(this, BitmapActivity::class.java)
      startActivity(intent)
    }

    binding.btnViewTraversal.setOnClickListener {
      var intent = Intent(this, ViewTraversalActivity::class.java)
      startActivity(intent)
    }

    binding.btnViewMeasure.setOnClickListener {
      var intent = Intent(this, ViewMeasureActivity::class.java)
      startActivity(intent)
    }

    binding.btnTouchEvent.setOnClickListener {
      var intent = Intent(this, TouchEventActivity::class.java)
      startActivity(intent)
    }

    binding.btnLayoutInflater.setOnClickListener {
      var intent = Intent(this, LayoutInflaterActivity::class.java)
      startActivity(intent)
    }

    binding.btnFps.setOnClickListener {
      var intent = Intent(this, FpsMonitorActivity::class.java)
      startActivity(intent)
    }

    binding.btnHandler.setOnClickListener {
      var intent = Intent(this, HandlerActivity::class.java)
      startActivity(intent)
    }

    binding.btnToast.setOnClickListener {
//            Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show()
      // 小米手机Toast会默认带上应用名
      // 解决方案1
      var toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)
      toast.setText("toast")
      toast.show()
      // https://mp.weixin.qq.com/s/B3ooLGa-uQK4pf9RrZvY5g
    }

    binding.btnCoordinatorLayout.setOnClickListener {
      var intent = Intent(this, CoordinatorLayoutActivity::class.java)
      startActivity(intent)
    }

    binding.btnBannerLayout.setOnClickListener {
      var intent = Intent(this, BannerActivity::class.java)
      startActivity(intent)
    }

    binding.btnSurfaceView.setOnClickListener {
      var intent = Intent(this, SurfaceViewActivity::class.java)
      startActivity(intent)
    }

    binding.btnAidl.setOnClickListener {
      var intent = Intent(this, AidlActivity::class.java)
      startActivity(intent)
    }

    binding.btnNotification.setOnClickListener {
      var intent = Intent(this, NotificationActivity::class.java)
      startActivity(intent)
    }

    binding.btnTabLayout.setOnClickListener {
      var intent = Intent(this, TabLayoutActivity::class.java)
      startActivity(intent)
    }

    binding.btnDrawableTint.setOnClickListener {
      var intent = Intent(this, DrawableTintActivity::class.java)
      startActivity(intent)
    }

    binding.btnService.setOnClickListener {
      var intent = Intent(this, ServiceActivity::class.java)
      startActivity(intent)
    }

    binding.btnTaskAffinity.setOnClickListener {
      var intent = Intent(this, StandardActivity::class.java)
      startActivity(intent)
    }

    binding.btnFadingEdge.setOnClickListener {
      var intent = Intent(this, FadingEdgeActivity::class.java)
      startActivity(intent)
    }

    binding.btnDataBinding.setOnClickListener {
      var intent = Intent(this, DataBindingActivity::class.java)
      startActivity(intent)
    }

    binding.btnShowDialog.setOnClickListener {
      var dialog = AlertDialog.Builder(this)
        .setTitle("Alert Dialog")
        .create()
      dialog.show()
    }

    binding.btnLottie.setOnClickListener {
      var intent = Intent(this, LottieActivity::class.java)
      startActivity(intent)
    }

    binding.btnDrawableAnim.setOnClickListener {
      var intent = Intent(this, DrawableAnimActivity::class.java)
      startActivity(intent)
    }

    binding.btnCustomView.setOnClickListener {
      var intent = Intent(this, CustomActivity::class.java)
      startActivity(intent)
    }

    binding.btnConstraintLayout.setOnClickListener {
      var intent = Intent(this, ConstraintLayoutActivity::class.java)
      startActivity(intent)
    }

    binding.btnOkio.setOnClickListener {
      var intent = Intent(this, OkioActivity::class.java)
      startActivity(intent)
    }

    // 正式环境可以抽出一个工具类
    var myActivityLauncher =
      registerForActivityResult(MyActivityResultContract(), ActivityResultCallback {
        Toast.makeText(this, "结果为：$it", Toast.LENGTH_LONG).show()
      })
    var takePicture = registerForActivityResult(
      ActivityResultContracts.TakePicturePreview(),
      ActivityResultCallback {

      })
    binding.btnNewActResult.setOnClickListener {
//            myActivityLauncher.launch("Hello World !!")
      takePicture.launch(null)
    }

    binding.btnDb.setOnClickListener {
      val intent = Intent(this, DBActivity::class.java)
      startActivity(intent)
    }

    //WorkHelper.startWorker(this)
  }

  /**
   * 通过更改scaledDensity来更改文字的大小，只对SP有用
   *
   * 0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
   */
  private fun updateFontScale(scale: Float = 1f) {
    var configuration = resources.configuration
    configuration.fontScale = scale

    var metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    metrics.scaledDensity = configuration.fontScale * metrics.density

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      baseContext.resources.displayMetrics.scaledDensity = metrics.scaledDensity
      baseContext.createConfigurationContext(configuration)
    } else {
      baseContext.resources.updateConfiguration(configuration, metrics)
    }
  }
}
