package com.kevin.demo.work;

import android.content.Context;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.Timer;
import java.util.concurrent.TimeUnit;


/**
 * Create by Kevin-Tu on 2019/12/23.
 */
public class WorkHelper {

    public static void startWorker(Context context) {
        // 约束
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        // 参数，键值对的形式
        Data data = new Data.Builder().putString("ky", "value")
                .build();

        // tag
        //WorkManager.getInstance(context.getApplicationContext()).getWorkInfosByTag("tag");
        //WorkManager.getInstance(context.getApplicationContext())..cancelAllWorkByTag("tag");

        /*OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                .setConstraints(constraints)
                .setInputData(data)
                .addTag("tag")
                .build();*/

        //PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(NotificationWorker.class, 1, TimeUnit.HOURS)
        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(NotificationWorker.class, PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
                .setConstraints(constraints)
                .setInputData(data)
                .addTag("tag")
                .build();

        WorkManager.getInstance(context.getApplicationContext()).enqueue(request);



        // 通过LiveData监听Worker返回的数据。
        /*WorkManager.getInstance(context).getWorkInfoByIdLiveData(request.getId()).observe(this, Observer {

        })*/
    }
}
