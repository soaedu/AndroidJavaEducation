package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by OS1 on 19.12.2016.
 */
public class Lesson74_RunnableUIThread  extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private TextView mInfoTv;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson74);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson74_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mInfoTv = (TextView) findViewById(R.id.Lesson74_Info_TV);

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);

                    TimeUnit.SECONDS.sleep(1);
                    mInfoTv.postDelayed(runn3, 2000);

                    mInfoTv.post(runn2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson74_ArrowBackWrapLL:
                startActivity(new Intent(Lesson74_RunnableUIThread.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }

    Runnable runn1 = new Runnable() {
        public void run() {
            mInfoTv.setText("runn1");
        }
    };

    Runnable runn2 = new Runnable() {
        public void run() {
            mInfoTv.setText("runn2");
        }
    };

    Runnable runn3 = new Runnable() {
        public void run() {
            mInfoTv.setText("runn3");
        }
    };
}