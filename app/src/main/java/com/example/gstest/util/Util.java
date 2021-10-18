package com.example.gstest.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Util {

    public static Boolean isConnectedToNetwork(Context context) {
        if(context!=null) {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isAvailable() && activeNetwork.isConnected()&&
                    activeNetwork.isConnectedOrConnecting();

            try {
                return isConnected;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
