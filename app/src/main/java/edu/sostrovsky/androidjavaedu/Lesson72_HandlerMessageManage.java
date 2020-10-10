package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.12.2016.
 */
public class Lesson72_HandlerMessageManage  extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Handler handler;

    private Handler.Callback hc = new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            Log.d(LOG_TAG, "what = " + msg.what);
            return false;
        }
    };

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson72);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson72_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        handler = new Handler(hc);

        sendMessages();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson72_ArrowBackWrapLL:
                startActivity(new Intent(Lesson72_HandlerMessageManage.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }

    void sendMessages() {
        Log.d(LOG_TAG, "send messages");

//        handler.sendEmptyMessageDelayed(1, 1000);
//        handler.sendEmptyMessageDelayed(2, 2000);
//        handler.sendEmptyMessageDelayed(3, 3000);

        handler.sendEmptyMessageDelayed(1, 1000);
        handler.sendEmptyMessageDelayed(2, 2000);
        handler.sendEmptyMessageDelayed(3, 3000);
        handler.sendEmptyMessageDelayed(2, 4000);
        handler.sendEmptyMessageDelayed(5, 5000);
        handler.sendEmptyMessageDelayed(2, 6000);
        handler.sendEmptyMessageDelayed(7, 7000);

        handler.removeMessages(2);
    }
}