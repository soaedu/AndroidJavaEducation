package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 25.12.2016.
 */
public class Lesson87_ServiceBindingLocal   extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private boolean bound = false;

    private ServiceConnection sConn;

    private Intent intent;

    private Lesson87_MyService myService;

    private TextView mIntervalTV;

    private long interval;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson87);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson87_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////

        mIntervalTV = (TextView) findViewById(R.id.Lesson87_Interval_TV);

        intent = new Intent(this, Lesson87_MyService.class);

        sConn = new ServiceConnection() {

            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(LOG_TAG, "Lesson87_ServiceBindingLocal onServiceConnected");

                myService = ((Lesson87_MyService.MyBinder) binder).getService();
                bound = true;
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "Lesson87_ServiceBindingLocal onServiceDisconnected");

                bound = false;
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(intent, sConn, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!bound) return;
        unbindService(sConn);
        bound = false;
    }



    protected void onDestroy() {
        super.onDestroy();

        if (!bound) return;
        unbindService(sConn);
        bound = false;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson87_ArrowBackWrapLL:
                startActivity(new Intent(Lesson87_ServiceBindingLocal.this, Main_Activity.class));
                break;
            case R.id.Lesson87_Start_Btn:
                startService(intent);
                break;
            case R.id.Lesson87_Up_Btn:
                if (!bound) return;
                interval = myService.upInterval(500);
                mIntervalTV.setText("interval = " + interval);
                break;
            case R.id.Lesson87_Down_Btn:
                if (!bound) return;
                interval = myService.downInterval(500);
                mIntervalTV.setText("interval = " + interval);
                break;
            default:
                break;
        }
    }
}