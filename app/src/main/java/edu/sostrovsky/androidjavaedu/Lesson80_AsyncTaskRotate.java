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
public class Lesson80_AsyncTaskRotate extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private MyTask mt;

    private TextView mInfoTV;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson80);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson80_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        Log.d(LOG_TAG, "create MainActivity: " + this.hashCode());

        mInfoTV = (TextView) findViewById(R.id.Lesson80_Info_TV);

//        mt = new MyTask();
//        Log.d("qwe", "create MyTask: " + mt.hashCode());
//        mt.execute();

        mt = (MyTask) getLastNonConfigurationInstance();
        if (mt == null) {
            mt = new MyTask();
            mt.execute();
        }

        // передаем в MyTask ссылку на текущее MainActivity
        mt.link(this);

        Log.d(LOG_TAG, "create MyTask: " + mt.hashCode());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson80_ArrowBackWrapLL:
                startActivity(new Intent(Lesson80_AsyncTaskRotate.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }

    public Object onRetainNonConfigurationInstance() {
        // удаляем из MyTask ссылку на старое MainActivity
        mt.unLink();
        return mt;
    }

    static class MyTask extends AsyncTask<String, Integer, Void> {

        Lesson80_AsyncTaskRotate mActivity;

        // получаем ссылку на MainActivity
        void link(Lesson80_AsyncTaskRotate act) {
            mActivity = act;
        }

        // обнуляем ссылку
        void unLink() {
            mActivity = null;
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
//                for (int i = 1; i <= 10; i++) {
//                    TimeUnit.SECONDS.sleep(1);
//                    publishProgress(i);
//                    Log.d(LOG_TAG, "i = " + i + ", MyTask: " + this.hashCode() + ", Lesson80_AsyncTaskRotate: " + Lesson80_AsyncTaskRotate.this.hashCode());
//                }

                for (int i = 1; i <= 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);

                    if(mActivity != null)
                        Log.d("qwe", "i = " + i + ", MyTask: " + this.hashCode() + ", Lesson80_AsyncTaskRotate: " + mActivity.hashCode());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            if(mActivity != null)
                mActivity.mInfoTV.setText("i = " + values[0]);

        }
    }
}