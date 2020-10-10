package edu.sostrovsky.androidjavaedu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by OS1 on 08.01.2017.
 */
public class Lesson107_Receiver extends BroadcastReceiver {

    private final String LOG_TAG = "myLogs";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG, "onReceive");
        Log.d(LOG_TAG, "action = " + intent.getAction());
        Log.d(LOG_TAG, "extra = " + intent.getStringExtra("extra"));
    }
}