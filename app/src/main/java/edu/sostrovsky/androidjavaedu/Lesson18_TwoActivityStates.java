package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson18_TwoActivityStates extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mBTN;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson18);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson18_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mBTN = (Button) findViewById(R.id.Lesson18_BTN);
        mBTN.setOnClickListener(this);

        Log.d(LOG_TAG, "Lesson18_TwoActivityStates: onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(LOG_TAG, "Lesson18_TwoActivityStates: onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "Lesson18_TwoActivityStates: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Lesson18_TwoActivityStates: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Lesson18_TwoActivityStates: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(LOG_TAG, "Lesson18_TwoActivityStates: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(LOG_TAG, "Lesson18_TwoActivityStates: onDestroy()");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson18_ArrowBackWrapLL: startActivity(new Intent(Lesson18_TwoActivityStates.this, Main_Activity.class));
                                                break;
            case R.id.Lesson18_BTN:             startActivity(new Intent(Lesson18_TwoActivityStates.this, Lesson18_SecondActivity.class));
                                                break;
        }
    }
}