package com.kevin.demo.work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Create by Kevin-Tu on 2019/12/23.
 */
public class NotificationWorker extends Worker {

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        android.util.Log.v("kevintu", "doWork() start time:" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            android.util.Log.v("kevintu", "doWork() catch time:" + System.currentTimeMillis() + "  e: " + e);
        }
        android.util.Log.v("kevintu", "doWork() end time:" + System.currentTimeMillis());
        return Result.success();
        // 返回结果中带有数据
        //return Result.success(new Data.Builder().build());
    }
}
