package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by OS1 on 19.12.2016.
 */
public class Lesson73_HandlerRunnable   extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private ProgressBar mCountPb;
    private TextView mInfoTv;
    private CheckBox mInfoChb;

    private int cnt;

    private final int max = 100;

    private Handler handler;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson73);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson73_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        handler = new Handler();

        mCountPb = (ProgressBar) findViewById(R.id.Lesson73_Count_PB);
        mCountPb.setMax(max);
        mCountPb.setProgress(0);

        mInfoTv = (TextView) findViewById(R.id.Lesson73_Info_TV);

        mInfoChb = (CheckBox) findViewById(R.id.Lesson73_Info_CHB);
        mInfoChb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mInfoTv.setVisibility(View.VISIBLE);

                    // показываем информацию
                    handler.post(showInfo);
                } else {
                    mInfoTv.setVisibility(View.GONE);

                    // отменяем показ информации
                    handler.removeCallbacks(showInfo);
                }
            }
        });

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    for (cnt = 1; cnt < max; cnt++) {
                        TimeUnit.MILLISECONDS.sleep(100);

                        // обновляем ProgressBar
                        handler.post(updateProgress);
                    }
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
            case R.id.Lesson73_ArrowBackWrapLL:
                startActivity(new Intent(Lesson73_HandlerRunnable.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }

    // обновление ProgressBar
    private Runnable updateProgress = new Runnable() {
        public void run() {
            mCountPb.setProgress(cnt);
        }
    };

    // показ информации
    private Runnable showInfo = new Runnable() {
        public void run() {
            Log.d(LOG_TAG, "showInfo");

            mInfoTv.setText("Count = " + cnt);

            // планирует сам себя через 1000 мсек
            handler.postDelayed(showInfo, 1000);
        }
    };
}