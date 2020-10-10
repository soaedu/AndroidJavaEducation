package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by OS1 on 20.12.2016.
 */
public class Lesson76_AsyncTaskParams extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private MyTask mt;

    private TextView mInfoTV;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson76);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson76_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mInfoTV = (TextView) findViewById(R.id.Lesson76_Info_TV);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson76_ArrowBackWrapLL:
                startActivity(new Intent(Lesson76_AsyncTaskParams.this, Main_Activity.class));
                break;
            case R.id.Lesson76_Start_Btn:
                mt = new MyTask();
                mt.execute("file_path_1", "file_path_2", "file_path_3", "file_path_4");
                break;
            default:
                break;
        }
    }

    class MyTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mInfoTV.setText(getResources().getString(R.string.start_text));
        }

        @Override
        protected Void doInBackground(String... urls) {
            try {
                int cnt = 0;

                for (String url : urls) {
                    // загружаем файл
                    downloadFile(url);

                    // выводим промежуточные результаты
                    publishProgress(++cnt);
                }

                // разъединяемся
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mInfoTV.setText("Downloaded " + values[0] + " files");
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mInfoTV.setText(getResources().getString(R.string.end_text));
        }

        private void downloadFile(String url) throws InterruptedException {
            TimeUnit.SECONDS.sleep(2);
        }
    }
}