package com.kevin.demo.module.vpn;

import android.content.Intent;
import android.net.VpnService;

/**
 * Created by tuchuantao on 2021/8/23
 * Desc:
 */
public class MyVpnService extends VpnService {
    private final static String ACTION_START_VPN_SERVICE = "com.kevin.demo.VpnService.Action.Start";
    private final static String ACTION_STOP_VPN_SERVICE = "com.kevin.demo.VpnService.Action.Stop";


    public static void startService() {
        Intent intent = new Intent();
    }

    public static void stopService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
