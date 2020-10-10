package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

import java.util.List;

/**
 * Created by OS1 on 03.01.2017.
 */
public abstract class Lesson104_MngTasks1   extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    private List<ActivityManager.RunningTaskInfo> mList;

    private ActivityManager mActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson104);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson104_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////////

        setTitle(getResources().getString(R.string.app_name) + " : " + getLocalClassName());

        mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    }

    public void onInfoClick(View v) {
        mList = mActivityManager.getRunningTasks(10);

        for (ActivityManager.RunningTaskInfo task : mList) {
            if (task.baseActivity.flattenToShortString().startsWith("com.example.os1")){
                Log.d(LOG_TAG, "------------------");
                Log.d(LOG_TAG, "Count: " + task.numActivities);
                Log.d(LOG_TAG, "Root: " + task.baseActivity.flattenToShortString());
                Log.d(LOG_TAG, "Top: " + task.topActivity.flattenToShortString());
            }
        }
    }

    abstract public void onClick(View v);

//    @Override
//    public void onClick(View view) {
//
//        switch (view.getId()) {
//            case R.id.Lesson104_ArrowBackWrapLL:
//                startActivity(new Intent(Lesson104_MngTasks1.this, Main_Activity.class));
//                break;
//            default:
//                break;
//        }
//    }
}