package edu.sostrovsky.androidjavaedu;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by OS1 on 25.12.2016.
 */

public class Lesson87_MyService extends Service {

    private final String LOG_TAG = "myLogs";

    MyBinder binder = new MyBinder();

    Timer timer;
    TimerTask tTask;

    long interval = 1000;

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");

        timer = new Timer();
        schedule();
    }

    void schedule() {
        if (tTask != null) tTask.cancel();

        if (interval > 0) {
            tTask = new TimerTask() {
                public void run() {
                    Log.d(LOG_TAG, "run");
                }
            };

            timer.schedule(tTask, 1000, interval);
        }
    }

    long upInterval(long gap) {
        interval = interval + gap;
        schedule();
        return interval;
    }

    long downInterval(long gap) {
        interval = interval - gap;
        if (interval < 0) interval = 0;
        schedule();
        return interval;
    }

    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "MyService onBind");
        return binder;
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(LOG_TAG, "MyService onRebind");
    }

    class MyBinder extends Binder {
        Lesson87_MyService getService() {
            return Lesson87_MyService.this;
        }
    }
}
