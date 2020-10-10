package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson17_ActivityStates extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson17);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson17_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        Log.d(LOG_TAG, "Lesson17_ActivityStates: onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "Lesson17_ActivityStates: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Lesson17_ActivityStates: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Lesson17_ActivityStates: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(LOG_TAG, "Lesson17_ActivityStates: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(LOG_TAG, "Lesson17_ActivityStates: onDestroy()");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson17_ArrowBackWrapLL: startActivity(new Intent(Lesson17_ActivityStates.this, Main_Activity.class));
                                                break;
        }
    }
}