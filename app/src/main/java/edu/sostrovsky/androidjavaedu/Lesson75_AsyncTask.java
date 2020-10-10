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
public class Lesson75_AsyncTask extends     Activity
                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private MyTask mt;

    private TextView mInfoTV;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson75);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson75_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mInfoTV = (TextView) findViewById(R.id.Lesson75_Info_TV);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson75_ArrowBackWrapLL:
                startActivity(new Intent(Lesson75_AsyncTask.this, Main_Activity.class));
                break;
            case R.id.Lesson75_Start_Btn:
                mt = new MyTask();
                mt.execute();
                break;
            default:
                break;
        }
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mInfoTV.setText(getResources().getString(R.string.start_text));
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mInfoTV.setText(getResources().getString(R.string.end_text));
        }
    }
}