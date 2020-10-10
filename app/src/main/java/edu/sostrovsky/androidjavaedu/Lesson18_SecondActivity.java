package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson18_SecondActivity    extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson18_second_activity);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson18_SecondActivity_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        StringBuilder sb = new StringBuilder("");
        sb.append(getResources().getString(R.string.lesson18_two_activities_lifecycle_title));
        sb.append(" -> ");
        sb.append(getResources().getString(R.string.second_activity_text));

        TextView title = (TextView) findViewById(R.id.Lesson18_SecondActivityTitle_TV);
        title.setText(sb.toString());

        Log.d(LOG_TAG, "Lesson18_SecondActivity: onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(LOG_TAG, "Lesson18_SecondActivity: onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "Lesson18_SecondActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Lesson18_SecondActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Lesson18_SecondActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(LOG_TAG, "Lesson18_SecondActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(LOG_TAG, "Lesson18_SecondActivity: onDestroy()");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson18_SecondActivity_ArrowBackWrapLL:  startActivity(new Intent(Lesson18_SecondActivity.this, Lesson18_TwoActivityStates.class));
                                                                break;
        }
    }
}