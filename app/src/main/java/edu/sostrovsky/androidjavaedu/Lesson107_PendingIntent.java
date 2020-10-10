package edu.sostrovsky.androidjavaedu;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 08.01.2017.
 */
public class Lesson107_PendingIntent extends AppCompatActivity
        implements View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    private NotificationManager nm;
    private AlarmManager am;

    private Intent intent1;
    private Intent intent2;

    private PendingIntent pIntent1;
    private PendingIntent pIntent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson107);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson107_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////////

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson107_ArrowBackWrapLL:
                startActivity(new Intent(Lesson107_PendingIntent.this, Main_Activity.class));
                break;
            case R.id.Lesson107_First_Btn:
                /*
//                intent1 = createIntent("action 1", "extra 1");
//                pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);
//
//                intent2 = createIntent("action 2", "extra 2");
//                pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, 0);

                intent1 = createIntent("action", "extra 1");
                pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);

                intent2 = createIntent("action", "extra 2");
//                pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, 0);
//                pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
                pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

                compare();

                sendNotif(1, pIntent1);
                sendNotif(2, pIntent2);
*/
                ////////////////////////////////////////////////////////////////////////////////

                /*
                intent1 = createIntent("action", "extra 1");
//                pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);
                pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, PendingIntent.FLAG_ONE_SHOT);

                sendNotif(1, pIntent1);
                sendNotif(2, pIntent1);
                */

                ////////////////////////////////////////////////////////////////////////////////

                /*
                intent1 = createIntent("action", "extra 1");
                pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);
                Log.d(LOG_TAG, "pIntent1 created");*/

                ////////////////////////////////////////////////////////////////////////////////

                /*
                intent1 = createIntent("action", "extra 1");
                pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);
                sendNotif(1, pIntent1);
                */

                ////////////////////////////////////////////////////////////////////////////////

                /*
                intent1 = createIntent("action", "extra 1");
                pIntent1 = PendingIntent.getBroadcast(this, 1, intent1, 0);

                intent2 = createIntent("action", "extra 2");
                pIntent2 = PendingIntent.getBroadcast(this, 2, intent2, 0);

                compare();

                sendNotif(1, pIntent1);
                sendNotif(2, pIntent2);
                */

                ////////////////////////////////////////////////////////////////////////////////

                /*
                intent1 = createIntent("action", "extra 1");
                Uri data1 = Uri.parse(intent1.toUri(Intent.URI_INTENT_SCHEME));
                intent1.setData(data1);
                pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);

                intent2 = createIntent("action", "extra 2");
                Uri data2 = Uri.parse(intent2.toUri(Intent.URI_INTENT_SCHEME));
                intent2.setData(data2);
                pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, 0);

                compare();

                sendNotif(1, pIntent1);
                sendNotif(2, pIntent2);
                */

                ////////////////////////////////////////////////////////////////////////////////

                /*
                intent1 = createIntent("action 1", "extra 1");
                pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);

                intent2 = createIntent("action 2", "extra 2");
                pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, 0);

                Log.d(LOG_TAG, "start");
                am.set(AlarmManager.RTC, System.currentTimeMillis() + 4000, pIntent1);
                am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 3000, 5000, pIntent2);
                */

                ////////////////////////////////////////////////////////////////////////////////

                intent1 = createIntent("action", "extra 1");
                pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);

                intent2 = createIntent("action", "extra 2");
                pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, 0);

                Log.d("qwe", "start");
                am.set(AlarmManager.RTC, System.currentTimeMillis() + 2000, pIntent1);
                am.set(AlarmManager.RTC, System.currentTimeMillis() + 4000, pIntent2);

                break;
            case R.id.Lesson107_Second_Btn:
//                intent2 = createIntent("action", "extra 2");
//                pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, PendingIntent.FLAG_NO_CREATE);
//                if (pIntent2 == null) Log.d(LOG_TAG, "pIntent2 is null");
//                else Log.d(LOG_TAG, "pIntent2 created");

                ////////////////////////////////////////////////////////////////////////////////

//                pIntent1.cancel();
//                Log.d(LOG_TAG, "cancel pIntent1");

                ////////////////////////////////////////////////////////////////////////////////

                am.cancel(pIntent2);

                break;
            default:
                break;
        }
    }

    private Intent createIntent(String action, String extra) {
        Intent intent = new Intent(this, Lesson107_Receiver.class);
        intent.setAction(action);
        intent.putExtra("extra", extra);
        return intent;
    }

    private void compare() {
        Log.d(LOG_TAG, "intent1 = intent2: " + intent1.filterEquals(intent2));
        Log.d(LOG_TAG, "pIntent1 = pIntent2: " + pIntent1.equals(pIntent2));
    }

    private void sendNotif(int id, PendingIntent pIntent) {
        Notification notif = new Notification(R.mipmap.ic_launcher, "Notif " + id, System.currentTimeMillis());
        notif.flags |= Notification.FLAG_AUTO_CANCEL;
        // notif.setLatestEventInfo(this, "Title " + id, "Content " + id, pIntent);
        nm.notify(id, notif);
    }
}