package com.kevin.demo.module.okio;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tuchuantao on 2020/11/7
 */
public class OKHttpUtils {

    public static final String TAG = "OKHttpUtils";
    private static volatile OKHttpUtils mInstance;
    private OkHttpClient mClient;

    public static OKHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (OKHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OKHttpUtils();
                }
            }
        }
        return mInstance;
    }

    private OKHttpUtils() {
        mClient = new OkHttpClient.Builder()
                //.addInterceptor()
                //.cache()
                .build();
    }

    public void executeRequest() {
        try {
            Request request = createRequest();
            Call call = mClient.newCall(request);
            Response response = call.execute();
            Log.v(TAG, "executeRequest() response: " + response);
        } catch (Exception e) {
            Log.v(TAG, "executeRequest() e: " + e);
        }
    }

    public void executeRequestWithThread() {
        new Thread(() -> {
            Request request = createRequest();
            Call call = mClient.newCall(request);
            Response response = null;
            try {
                response = call.execute();
                Log.v(TAG, "executeRequestWithThread() response: " + response);
                Log.v(TAG, "response body: " + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void enqueueRequest() {
        Request request = createRequest();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v(TAG, "enqueueRequest() onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v(TAG, "enqueueRequest() onResponse: " + response);
            }
        });
    }

    private Request createRequest() {
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/contributors")
                //.addHeader()
                //.method()
                //.cacheControl()
                .build();
        return request;
    }
}
