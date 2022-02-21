package com.kevin.demo.module.okio;

import android.os.Bundle;
import android.util.Log;

import com.kevin.demo.R;
import com.kevin.demo.base.BaseActivity;
import com.kevin.demo.databinding.ActivityOkioBinding;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;

/**
 * Created by tuchuantao on 2020/10/27
 */
public class OkioActivity extends BaseActivity<ActivityOkioBinding> {

    private static final String TAG = OkioActivity.class.getSimpleName();

    @Override
    public int getLayoutResId() {
        return R.layout.activity_okio;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        try {
            URL url = new URL("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=394037840,3437258487&fm=26&gp=0.jpg");
            Log.v("kevin", "getHost: " + url.getHost());
            Log.v("kevin", "getPath: " + url.getPath());
            Log.v("kevin", "getProtocol: " + url.getProtocol());
//            Log.v("kevin", "getContent: " + url.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        binding.btnIo.setOnClickListener(view -> {
            FileUtils.ioCopyFile();
        });

        binding.btnNio.setOnClickListener(view -> {
            FileUtils.nioCopyFile();
        });

        binding.btnOkio.setOnClickListener(view -> {
            FileUtils.okioCopyFile();
        });

        IOUtils ioUtils = new IOUtils();
        binding.btnIoServer.setOnClickListener(view -> {
            ioUtils.startServer();
        });

        binding.btnIoClient.setOnClickListener(view -> {
            ioUtils.startClient();
        });

        binding.btnNioServer.setOnClickListener(view -> {
            new LocalServerSocket().startServer();
        });

        binding.btnNioClient.setOnClickListener(view -> {
            new LocalSocketClient().startClient();
        });

        binding.btnExecute.setOnClickListener(view -> {
            OKHttpUtils.getInstance().executeRequest();
        });

        binding.btnExecuteWithThread.setOnClickListener(view -> {
            OKHttpUtils.getInstance().executeRequestWithThread();
        });

        binding.btnEnqueue.setOnClickListener(view -> {
            OKHttpUtils.getInstance().enqueueRequest();
        });
    }

}
