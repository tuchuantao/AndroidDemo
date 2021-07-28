package com.kevin.demo.module.handler;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.kevin.demo.R;

import java.lang.reflect.Field;

/**
 * Created by tuchuantao on 2021/7/21
 * Desc:
 */
public class HandlerActivity extends Activity {

    private TextView mContentView;
    private MyView mCustomView;
    private Handler mHandler = new Handler();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        mContentView = findViewById(R.id.content);
        mCustomView = findViewById(R.id.my_view);

        MessageQueue queue = mHandler.getLooper().getQueue();
        try {
            Field field = MessageQueue.class.getDeclaredField("mMessages");
            field.setAccessible(true);
            Message blocked = (Message) field.get(queue);
            Log.v("Kevin", "111111blocked=" + blocked + " hashCode=" + blocked.hashCode());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            Log.v("Kevin", "Exception=" + e);
        }

        mHandler.post(() -> {
            Log.v("Kevin", "onCreate() post width=" + mContentView.getWidth() + "  height=" + mContentView.getHeight());
            Log.v("Kevin", "onCreate() post mCustomView width=" + mCustomView.getWidth() + "  height=" + mCustomView.getHeight());
        });

        try {
            Field field = MessageQueue.class.getDeclaredField("mMessages");
            field.setAccessible(true);
            Message blocked = (Message) field.get(queue);
//            boolean blocked = field.getBoolean(queue);
            Log.v("Kevin", "blocked=" + blocked + " hashCode=" + blocked.hashCode());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            Log.v("Kevin", "Exception=" + e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Kevin", "onResume() width=" + mContentView.getWidth() + "  height=" + mContentView.getHeight());
        Log.v("Kevin", "onResume() mCustomView width=" + mCustomView.getWidth() + "  height=" + mCustomView.getHeight());

        MessageQueue queue = mHandler.getLooper().getQueue();
        try {
            Field field = MessageQueue.class.getDeclaredField("mMessages");
            field.setAccessible(true);
            Message blocked = (Message) field.get(queue);
//            boolean blocked = field.getBoolean(queue);
            Log.v("Kevin", "22222blocked=" + blocked + " hashCode=" + blocked.hashCode());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            Log.v("Kevin", "Exception=" + e);
        }


        mHandler.post(() -> {
            try {
                Field field = MessageQueue.class.getDeclaredField("mMessages");
                field.setAccessible(true);
                Message blocked = (Message) field.get(queue);
//            boolean blocked = field.getBoolean(queue);
                Log.v("Kevin", "44444blocked=" + blocked + " hashCode=" + blocked.hashCode());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                Log.v("Kevin", "Exception=" + e);
            }

            Log.v("Kevin", "post width=" + mContentView.getWidth() + "  height=" + mContentView.getHeight());
            Log.v("Kevin", "post mCustomView width=" + mCustomView.getWidth() + "  height=" + mCustomView.getHeight());
        });

        try {
            Field field = MessageQueue.class.getDeclaredField("mMessages");
            field.setAccessible(true);
            Message blocked = (Message) field.get(queue);
//            boolean blocked = field.getBoolean(queue);
            Log.v("Kevin", "3333333blocked=" + blocked + " hashCode=" + blocked.hashCode());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            Log.v("Kevin", "Exception=" + e);
        }
    }
}
