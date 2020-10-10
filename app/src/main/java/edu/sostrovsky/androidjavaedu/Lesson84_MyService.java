package edu.sostrovsky.androidjavaedu;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by OS1 on 22.12.2016.
 */

public class Lesson84_MyService extends Service {

    private ExecutorService es;

    private final String LOG_TAG = "myLogs";

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
        es = Executors.newFixedThreadPool(2);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand");

        int time = intent.getIntExtra(Lesson84_ServiceBackPendingIntent.PARAM_TIME, 1);

        PendingIntent pIntent = intent.getParcelableExtra(Lesson84_ServiceBackPendingIntent.PARAM_PINTENT);

        MyRun mr = new MyRun(time, startId, pIntent);
        es.execute(mr);

        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyRun implements Runnable {

        int time;
        int startId;
        PendingIntent pIntent;

        public MyRun(int time, int startId, PendingIntent pIntent) {
            this.time = time;
            this.startId = startId;
            this.pIntent = pIntent;
            Log.d(LOG_TAG, "MyRun#" + startId + " create");
        }

        public void run() {
            Log.d(LOG_TAG, "MyRun#" + startId + " start, time = " + time);
            try {
                // сообщаем об старте задачи
                pIntent.send(Lesson84_ServiceBackPendingIntent.STATUS_START);

                // начинаем выполнение задачи
                TimeUnit.SECONDS.sleep(time);

                // сообщаем об окончании задачи
                Intent intent = new Intent().putExtra(Lesson84_ServiceBackPendingIntent.PARAM_RESULT, time * 100);
                pIntent.send(Lesson84_MyService.this, Lesson84_ServiceBackPendingIntent.STATUS_FINISH, intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
            stop();
        }

        void stop() {
            Log.d(LOG_TAG, "MyRun#" + startId + " end, stopSelfResult(" + startId + ") = " + stopSelfResult(startId));
        }
    }
}
