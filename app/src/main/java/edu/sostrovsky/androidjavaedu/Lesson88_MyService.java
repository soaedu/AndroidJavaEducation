package edu.sostrovsky.androidjavaedu;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;

import com.sostrovsky.androidjavaedu.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by OS1 on 25.12.2016.
 */

public class Lesson88_MyService extends Service {

   NotificationManager notificationManager;

    public void onCreate() {
        super.onCreate();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sendNotification();
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotification() {

        // 1-я часть
        // Notification mNotification = new Notification(R.mipmap.ic_launcher, "Text in status bar", System.currentTimeMillis());

        // 3-я часть
        Intent intent = new Intent(this, Lesson88_ServiceNotification.class);
        intent.putExtra(Lesson88_ServiceNotification.FILE_NAME, "someFile");
        PendingIntent pIntent = PendingIntent.getActivity(this,0,intent,0);

        // 2-я часть
        // mNotification.setLatestEventInfo(this, "Notification's title", "Notification's text", pIntent);

        // ставим флаг, чтобы уведомление пропало после нажатия
        // mNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        // отправляем
        // notificationManager.notify(1, mNotification);

        //////////////////////////////////////////////////

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification's title")
                .setContentText("Notification's text")
                // .setSound(Uri)
                // .setSound(Uri.parse("file:///sdcard/notification/ringer.mp3");)
                .setSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6"))
                .setContentIntent(pIntent);

        Notification notification = builder.getNotification();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.flags |= Notification.DEFAULT_VIBRATE;
        notification.flags |= Notification.FLAG_ONGOING_EVENT;

        notification.number = 5;

        notificationManager.notify(R.mipmap.ic_launcher, notification);
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}