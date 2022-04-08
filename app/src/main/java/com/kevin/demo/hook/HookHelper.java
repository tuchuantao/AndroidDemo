package com.kevin.demo.hook;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.kevin.demo.DemoApplication;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by tuchuantao on 2022/4/7
 * Desc: Hook startActivity，支持打开不在Manifest中配置的Activity
 */
public class HookHelper {

  private static final String EXTRA_TARGET_INTENT = "target_intent";
  public static final int EXECUTE_TRANSACTION = 159;

  public static void startHook() {
    hookStartActivityEntrance();
    hookStartActivityExport();
  }

  /**
   * hook点：
   * 入口：
   * 1、Activity
   * mInstrumentation.execStartActivity
   * 2、IActivityManager
   * IActivityManagerSingleton.get()
   * ActivityManager.getService().startActivity
   * 3、ActivityThread
   * Instrumentation mInstrumentatio
   */
  public static void hookStartActivityEntrance() {
//    版本适配
//    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
//      Class activityManager = Class.forName("android.app.ActivityTaskManager");
//    } else {
//
//    }

    try {
      // 1、通过反射获取 ActivityManager 中的IActivityManagerSingleton属性
      Class activityManager = Class.forName("android.app.ActivityManager");
      Field singletonField = activityManager.getDeclaredField("IActivityManagerSingleton");
      singletonField.setAccessible(true);
      Object singletonObj = singletonField.get(null);
      Log.d("kevin", "singletonObj=" + singletonObj);

      // 2、获取Singleton的mInstance属性（IActivityManager）
      Class singletonClass = Class.forName("android.util.Singleton");
      Field mInstanceField = singletonClass.getDeclaredField("mInstance");
      mInstanceField.setAccessible(true);

      // 3、通过IActivityManagerSingleton对象获取 IActivityManager对象
      Object iActivityManagerObj = mInstanceField.get(singletonObj);
      Log.d("kevin", "iActivityManagerObj=" + iActivityManagerObj);

      // 4、创建一个IActivityManager的代理
      Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
          new Class[]{Class.forName("android.app.IActivityManager")},
          new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
              Log.d("kevin", "methodName=" + method.getName());
              Intent raw = null;
              int index = -1;
              if (TextUtils.equals("startActivity", method.getName())) {
                for (int i = 0; i < args.length; i++) {
                  if (args[i] instanceof Intent) {
                    raw = (Intent) args[i];
                    index = i;
                  }
                }
                Log.v("kevin", "raw=" + raw);
                Intent newIntent = new Intent();

                newIntent.setComponent(new ComponentName(DemoApplication.Companion.getSInstance().getPackageName(), SubstituteActivity.class.getName()));
                newIntent.putExtra(EXTRA_TARGET_INTENT, raw);
                args[index] = newIntent;
              }
              return method.invoke(iActivityManagerObj, args);
            }
          });
      // 5、将代理替换 mInstance
      mInstanceField.set(singletonObj, proxy);
    } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
      Log.d("kevin", "Exception=" + e);
      e.printStackTrace();
    }
  }

  /**
   * 出口：
   * ActivityThread.mH
   */
  public static void hookStartActivityExport() {
    try {
      // 1、获取ActivityThread对象
      Class activityThreadClass = Class.forName("android.app.ActivityThread");
      Field activityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
      activityThreadField.setAccessible(true);
      Object activityThreadObj = activityThreadField.get(null);
      Log.v("kevin", "activityThreadObj:" + activityThreadObj);

      // 2、获取 ActivityThread的 mH成员变量
      Field hField = activityThreadClass.getDeclaredField("mH");
      hField.setAccessible(true);
      Handler hObj = (Handler) hField.get(activityThreadObj);
      Log.v("kevin", "hObj:" + hObj);

      // 3、给ActivityThread 中的 mH 设置callback
      Field callbackField = Handler.class.getDeclaredField("mCallback");
      callbackField.setAccessible(true);
      callbackField.set(hObj, new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
          Log.v("kevin", "handleMessage() what=" + msg.what);
          switch (msg.what) {
            case 100: {
              // 7.0 以前的版本
            }
            case EXECUTE_TRANSACTION: {
              try {
                Log.v("kevin", "msg.obj=" + msg.obj);
                // 4、获取 msg.obj 并更改 Intent
                Field callbacksField = msg.obj.getClass().getDeclaredField("mActivityCallbacks");
                callbacksField.setAccessible(true);
                List callbacks = (List) callbacksField.get(msg.obj);
                Log.v("kevin", "callback size=" + callbacks.size());
                if (callbacks.size() > 0) {
                  String className = "android.app.servertransaction.LaunchActivityItem";
                  if (TextUtils.equals(callbacks.get(0).getClass().getCanonicalName(), className)) {
                    Object item = callbacks.get(0);
                    Field intentField = item.getClass().getDeclaredField("mIntent");
                    intentField.setAccessible(true);
                    Intent intent = (Intent) intentField.get(item);
                    Intent originIntent = intent.getParcelableExtra(EXTRA_TARGET_INTENT);
                    Log.v("kevin", "intent:" + intent + "  originIntent=" + originIntent);
                    intentField.set(item, originIntent);
//                    intent.setComponent(originIntent.getComponent());
                  }
                }
              } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
              }
              break;
            }
          }
          return false;
        }
      });
    } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
