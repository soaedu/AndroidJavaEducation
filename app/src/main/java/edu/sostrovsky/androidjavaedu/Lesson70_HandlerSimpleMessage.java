package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by OS1 on 19.12.2016.
 */
public class Lesson70_HandlerSimpleMessage extends     Activity
                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final int STATUS_NONE = 0;          // нет подключения
    private final int STATUS_CONNECTING = 1;    // подключаемся
    private final int STATUS_CONNECTED = 2;     // подключено

    private Handler handler;

    private TextView mStatusTv;
    private ProgressBar mConnectPb;
    private Button mConnectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson70);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson70_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mStatusTv = (TextView) findViewById(R.id.Lesson70_Status_TV);
        mConnectPb = (ProgressBar) findViewById(R.id.Lesson70_Connect_PB);
        mConnectBtn = (Button) findViewById(R.id.Lesson70_Connect_Btn);

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case STATUS_NONE:
                        mConnectBtn.setEnabled(true);
                        mStatusTv.setText(getResources().getString(R.string.not_connected_text));
                        break;
                    case STATUS_CONNECTING:
                        mConnectBtn.setEnabled(false);
                        mConnectPb.setVisibility(View.VISIBLE);
                        mStatusTv.setText(getResources().getString(R.string.connecting_text));
                        break;
                    case STATUS_CONNECTED:
                        mConnectPb.setVisibility(View.GONE);
                        mStatusTv.setText(getResources().getString(R.string.connected_text));
                        break;
                }
            };
        };

        handler.sendEmptyMessage(STATUS_NONE);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson70_ArrowBackWrapLL:
                startActivity(new Intent(Lesson70_HandlerSimpleMessage.this, Main_Activity.class));
                break;
            case R.id.Lesson70_Connect_Btn:
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            // устанавливаем подключение
                            handler.sendEmptyMessage(STATUS_CONNECTING);
                            TimeUnit.SECONDS.sleep(2);

                            // установлено
                            handler.sendEmptyMessage(STATUS_CONNECTED);

                            // выполняется какая-то работа
                            TimeUnit.SECONDS.sleep(3);

                            // разрываем подключение
                            handler.sendEmptyMessage(STATUS_NONE);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            default:
                break;
        }
    }

}