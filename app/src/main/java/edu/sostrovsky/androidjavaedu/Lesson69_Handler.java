package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by OS1 on 19.12.2016.
 */
public class Lesson69_Handler   extends     Activity
                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Handler handler;

    private TextView mInfoTv;
    private Button mStartBtn;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson69);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson69_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mInfoTv = (TextView) findViewById(R.id.Lesson69_Info_TV);

        mStartBtn = (Button) findViewById(R.id.Lesson69_Start_Btn);

        handler = new Handler() {

            public void handleMessage(android.os.Message msg) {

                // обновляем TextView
                mInfoTv.setText("Закачано файлов: " +msg.what);

                if(msg.what == 10)
                    mStartBtn.setEnabled(true);
            }
        };

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson69_ArrowBackWrapLL:
                startActivity(new Intent(Lesson69_Handler.this, Main_Activity.class));
                break;
            case R.id.Lesson69_Start_Btn:
//                for (int i = 1; i <= 10; i++) {
//                    // долгий процесс
//                    downloadFile();
//
//                    // обновляем TextView
//                    mInfoTv.setText("Закачано файлов: " + i);
//
//                    // пишем лог
//                    Log.d(LOG_TAG, "Закачано файлов: " + i);
//                }

                mStartBtn.setEnabled(false);

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 1; i <= 10; i++) {
                            // долгий процесс
                            downloadFile();

                            // обновляем TextView
                            // mInfoTv.setText("Закачано файлов: " + i);

                            // пишем лог
                            // Log.d(LOG_TAG, "Закачано файлов: " + i);


                            handler.sendEmptyMessage(i);

                            // пишем лог
                            Log.d(LOG_TAG, "i = " + i);
                        }
                    }
                });

                t.start();
                break;
            case R.id.Lesson69_Test_Btn:
                Log.d(LOG_TAG, "test");
            default:
                break;
        }
    }

    private void downloadFile() {
        // пауза - 1 секунда
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}