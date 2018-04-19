package com.ylet.sr.review;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStateReceiver extends BroadcastReceiver {
    public ChangeNetworkNotification changeNetworkNotification;

    public NetworkStateReceiver(ChangeNetworkNotification changeNetworkNotification) {
        super();
        this.changeNetworkNotification = changeNetworkNotification;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            boolean isConnected = false;
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                isConnected = networkInfo.isConnectedOrConnecting();
            }
            changeNetworkNotification.networkStateIsChanged(isConnected);
        }
    }
}
