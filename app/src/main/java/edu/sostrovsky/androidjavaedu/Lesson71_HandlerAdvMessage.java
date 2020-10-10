package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by OS1 on 19.12.2016.
 */
public class Lesson71_HandlerAdvMessage extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final int STATUS_NONE = 0; // нет подключения
    private final int STATUS_CONNECTING = 1; // подключаемся
    private final int STATUS_CONNECTED = 2; // подключено
    private final int STATUS_DOWNLOAD_START = 3; // загрузка началась
    private final int STATUS_DOWNLOAD_FILE = 4; // файл загружен
    private final int STATUS_DOWNLOAD_END = 5; // загрузка закончена
    private final int STATUS_DOWNLOAD_NONE = 6; // нет файлов для загрузки

    private Handler handler;

    private TextView mStatusTv;
    private ProgressBar mConnectPb;
    private Button mConnectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson71);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson71_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mStatusTv = (TextView) findViewById(R.id.Lesson71_Status_TV);
        mConnectPb = (ProgressBar) findViewById(R.id.Lesson71_Connect_PB);
        mConnectBtn = (Button) findViewById(R.id.Lesson71_Connect_Btn);

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case STATUS_NONE:
                        mConnectBtn.setEnabled(true);
                        mStatusTv.setText(getResources().getString(R.string.not_connected_text));
                        mConnectPb.setVisibility(View.GONE);
                        break;
                    case STATUS_CONNECTING:
                        mConnectBtn.setEnabled(false);
                        mStatusTv.setText(getResources().getString(R.string.connecting_text));
                        break;
                    case STATUS_CONNECTED:
                        mStatusTv.setText(getResources().getString(R.string.connected_text));
                        break;
                    case STATUS_DOWNLOAD_START:
                        mStatusTv.setText("Start download " + msg.arg1 + " files");
                        mConnectPb.setMax(msg.arg1);
                        mConnectPb.setProgress(0);
                        mConnectPb.setVisibility(View.VISIBLE);
                        break;
                    case STATUS_DOWNLOAD_FILE:
                        mStatusTv.setText("Downloading. Left " + msg.arg2 + " files");
                        mConnectPb.setProgress(msg.arg1);
                        saveFile((byte[]) msg.obj);
                        break;
                    case STATUS_DOWNLOAD_END:
                        mStatusTv.setText("Download complete!");
                        break;
                    case STATUS_DOWNLOAD_NONE:
                        mStatusTv.setText("No files for download");
                        break;
                }
            };
        };

        handler.sendEmptyMessage(STATUS_NONE);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson71_ArrowBackWrapLL:
                startActivity(new Intent(Lesson71_HandlerAdvMessage.this, Main_Activity.class));
                break;
            case R.id.Lesson71_Connect_Btn:
                Thread t = new Thread(new Runnable() {

                    Message msg;
                    byte[] file;
                    Random rand = new Random();

                    public void run() {
                        try {
                            // устанавливаем подключение
                            handler.sendEmptyMessage(STATUS_CONNECTING);
                            TimeUnit.SECONDS.sleep(1);

                            // подключение установлено
                            handler.sendEmptyMessage(STATUS_CONNECTED);

                            // определяем кол-во файлов
                            TimeUnit.SECONDS.sleep(1);
                            int filesCount = rand.nextInt(5);

                            if (filesCount == 0) {
                                // сообщаем, что файлов для загрузки нет
                                handler.sendEmptyMessage(STATUS_DOWNLOAD_NONE);

                                // и отключаемся
                                TimeUnit.MILLISECONDS.sleep(1500);
                                handler.sendEmptyMessage(STATUS_NONE);
                                return;
                            }

                            // загрузка начинается
                            // создаем сообщение, с информацией о количестве файлов
                            msg = handler.obtainMessage(STATUS_DOWNLOAD_START, filesCount, 0);

                            // отправляем
                            handler.sendMessage(msg);

                            for (int i = 1; i <= filesCount; i++) {
                                // загружается файл
                                file = downloadFile();

                                // создаем сообщение с информацией о порядковом номере
                                // файла,
                                // кол-вом оставшихся и самим файлом
                                msg = handler.obtainMessage(STATUS_DOWNLOAD_FILE, i, filesCount - i, file);

                                // отправляем
                                handler.sendMessage(msg);
                            }

                            // загрузка завершена
                            handler.sendEmptyMessage(STATUS_DOWNLOAD_END);

                            // отключаемся
                            TimeUnit.MILLISECONDS.sleep(1500);
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

    private byte[] downloadFile() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return new byte[1024];
    }

    private void saveFile(byte[] file) {

    }
}