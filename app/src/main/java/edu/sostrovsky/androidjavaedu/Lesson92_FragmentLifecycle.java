package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 28.12.2016.
 */
public class Lesson92_FragmentLifecycle extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson92);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson92_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        Log.d(LOG_TAG, "Lesson92_FragmentLifecycle onCreate");
    }

    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "Lesson92_FragmentLifecycle onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "Lesson92_FragmentLifecycle onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "Lesson92_FragmentLifecycle onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "Lesson92_FragmentLifecycle onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "Lesson92_FragmentLifecycle onDestroy");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson92_ArrowBackWrapLL:
                startActivity(new Intent(Lesson92_FragmentLifecycle.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }

}