package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by OS1 on 20.12.2016.
 */
public class Lesson78_AsyncTaskCancel extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private MyTask mt;

    private TextView mInfoTV;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson78);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson78_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mInfoTV = (TextView) findViewById(R.id.Lesson78_Info_TV);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson78_ArrowBackWrapLL:
                startActivity(new Intent(Lesson78_AsyncTaskCancel.this, Main_Activity.class));
                break;
            case R.id.Lesson78_Start_Btn:
                mt = new MyTask();
                mt.execute();
                break;
            case R.id.Lesson78_Cancel_Btn:
                cancelTask();
                break;
            default:
                break;
        }
    }

    private void cancelTask() {
        if (mt == null) return;

        Log.d(LOG_TAG, "cancel result: " + mt.cancel(false));

        Log.d(LOG_TAG, "cancel result: " + mt.cancel(true));
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            String msg = getResources().getString(R.string.start_text);

            mInfoTV.setText(msg);
            Log.d(LOG_TAG, msg);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
//                for (int i = 0; i < 5; i++) {
//                    TimeUnit.SECONDS.sleep(1);
//                    Log.d(LOG_TAG, "isCancelled: " + isCancelled());
//                }

                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);

//                    if (isCancelled()) return null;

                    Log.d(LOG_TAG, "isCancelled: " + isCancelled());
                }
            } catch (InterruptedException e) {
                Log.d(LOG_TAG, "Interrupted");
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            String msg = getResources().getString(R.string.end_text);

            mInfoTV.setText(msg);
            Log.d(LOG_TAG, msg);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            String msg = getResources().getString(R.string.cancel_text);

            mInfoTV.setText(msg);
            Log.d(LOG_TAG, msg);
        }
    }
}