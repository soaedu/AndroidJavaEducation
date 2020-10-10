package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sostrovsky.androidjavaedu.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by OS1 on 20.12.2016.
 */
public class Lesson79_AsyncTaskStatus extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private MyTask mt;

    private TextView mInfoTV;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson79);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson79_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mInfoTV = (TextView) findViewById(R.id.Lesson79_Info_TV);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson79_ArrowBackWrapLL:
                startActivity(new Intent(Lesson79_AsyncTaskStatus.this, Main_Activity.class));
                break;
            case R.id.Lesson79_Start_Btn:
                startTask();
                break;
            case R.id.Lesson79_Status_Btn:
                showStatus();
                break;
            default:
                break;
        }
    }

    private void startTask() {
        mt = new MyTask();
        mt.execute();
        mt.cancel(false);
    }

    private void showStatus() {
//        if (mt != null) Toast.makeText(this, mt.getStatus().toString(), Toast.LENGTH_SHORT).show();

        if (mt != null) {
            if (mt.isCancelled())
                Toast.makeText(this, "CANCELLED", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, mt.getStatus().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            String msg = getResources().getString(R.string.start_text);

            mInfoTV.setText(msg);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    if (isCancelled()) return null;

                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            String msg = getResources().getString(R.string.end_text);

            mInfoTV.setText(msg);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            String msg = getResources().getString(R.string.cancel_text);

            mInfoTV.setText(msg);
        }
    }
}