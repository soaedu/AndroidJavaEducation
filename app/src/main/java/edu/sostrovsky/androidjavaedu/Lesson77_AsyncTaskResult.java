package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sostrovsky.androidjavaedu.R;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by OS1 on 20.12.2016.
 */
public class Lesson77_AsyncTaskResult   extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private MyTask mt;

    private TextView mInfoTV;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson77);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson77_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mInfoTV = (TextView) findViewById(R.id.Lesson77_Info_TV);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson77_ArrowBackWrapLL:
                startActivity(new Intent(Lesson77_AsyncTaskResult.this, Main_Activity.class));
                break;
            case R.id.Lesson77_Start_Btn:
                mt = new MyTask();
                mt.execute();
                break;
            case R.id.Lesson77_Get_Result_Btn:
                showResult();
                break;
            default:
                break;
        }
    }

    private void showResult() {
//        if (mt == null) return;
//
//        int result = -1;
//
//        try {
//            Log.d(LOG_TAG, "Try to get result");
//
//            result = mt.get();
//
//            Log.d(LOG_TAG, "get returns " + result);
//
//            Toast.makeText(this, "get returns " + result, Toast.LENGTH_LONG).show();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        if (mt == null) return;

        int result = -1;

        try {
            Log.d(LOG_TAG, "Try to get result");
            result = mt.get(1, TimeUnit.SECONDS);
            Log.d(LOG_TAG, "get returns " + result);
            Toast.makeText(this, "get returns " + result, Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            Log.d(LOG_TAG, "get timeout, result = " + result);
            e.printStackTrace();
        }
    }


    class MyTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            String msg = getResources().getString(R.string.start_text);

            mInfoTV.setText(msg);
            Log.d(LOG_TAG, msg);
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100500;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            mInfoTV.setText("End. Result = " + result);

            Log.d(LOG_TAG, "End. Result = " + result);
        }
    }
}