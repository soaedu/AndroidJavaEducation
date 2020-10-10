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

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 23.12.2016.
 */
public class Lesson86_ServiceBindClient extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private boolean bound = false;

    private ServiceConnection sConn;

    private Intent intent;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson86);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson86_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////

        intent = new Intent("com.example.os1.start_android_education.Lesson86_MyService");

        sConn = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected");
                bound = true;
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };
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
            case R.id.Lesson86_ArrowBackWrapLL:
                startActivity(new Intent(Lesson86_ServiceBindClient.this, Main_Activity.class));
                break;
            case R.id.Lesson86_Start_Btn:
                startService(intent);
                break;
            case R.id.Lesson86_Stop_Btn:
                stopService(intent);
                break;
            case R.id.Lesson86_Bind_Btn:
                // bindService(intent, sConn, BIND_AUTO_CREATE);
                bindService(intent, sConn, 0);
                break;
            case R.id.Lesson86_Unbind_Btn:
                if (!bound) return;

                unbindService(sConn);
                bound = false;
                break;
            default:
                break;
        }
    }
}