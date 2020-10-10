package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 16.12.2016.
 */
public class Lesson59_SaveInstanceState extends     Activity
                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private int cnt = 0;

    private Button mBtn;

    private Lesson58_MyObject myObj;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson59);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson59_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////

        mBtn = (Button) findViewById(R.id.Lesson59_Btn);
        mBtn.setOnClickListener(this);

        Log.d(LOG_TAG, "onCreate");

        myObj = (Lesson58_MyObject) getLastNonConfigurationInstance();

        if(myObj != null)
            Log.d(LOG_TAG, "Obj_text= " +myObj.s+ " Obj_value= " +myObj.i);
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cnt = savedInstanceState.getInt("count");
        Log.d(LOG_TAG, "onRestoreInstanceState");
    }

    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume ");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", cnt);
        Log.d(LOG_TAG, "onSaveInstanceState");
    }

    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson59_ArrowBackWrapLL:
                startActivity(new Intent(Lesson59_SaveInstanceState.this, Main_Activity.class));
                break;
            case R.id.Lesson59_Btn:
                Toast.makeText(this, "Count = " + ++cnt, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public Object onRetainNonConfigurationInstance() {
        return new Lesson58_MyObject("test", 18);
    }
}