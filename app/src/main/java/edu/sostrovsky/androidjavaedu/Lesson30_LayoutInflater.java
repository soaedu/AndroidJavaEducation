package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson30_LayoutInflater extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson30);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson30_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        LayoutInflater ltInflater = getLayoutInflater();

        ///////////////////////////////////////////////////////////////////////////////////

//        View view = ltInflater.inflate(R.layout.text_view_lesson30, null, false);
//        ViewGroup.LayoutParams lp = view.getLayoutParams();
//
//        LinearLayout linLayout = (LinearLayout) findViewById(R.id.Lesson30_Layout_LL);
//        linLayout.addView(view);
//
//        Log.d(LOG_TAG, "Class of view: " + view.getClass().toString());
//        Log.d(LOG_TAG, "LayoutParams of view is null: " + (lp == null));
//        Log.d(LOG_TAG, "Text of view: " + ((TextView) view).getText());

        ///////////////////////////////////////////////////////////////////////////////////

//        LinearLayout linLayout = (LinearLayout) findViewById(R.id.Lesson30_Layout_LL);
//        View view1 = ltInflater.inflate(R.layout.text_view_lesson30, linLayout, false);
//        ViewGroup.LayoutParams lp1 = view1.getLayoutParams();
//
//        Log.d(LOG_TAG, "Class of view1: " + view1.getClass().toString());
//        Log.d(LOG_TAG, "Class of layoutParams of view1: " + lp1.getClass().toString());
//        Log.d(LOG_TAG, "Text of view1: " + ((TextView) view1).getText());
//
//        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.Lesson30_Layout_RL);
//        View view2 = ltInflater.inflate(R.layout.text_view_lesson30, relLayout, false);
//        ViewGroup.LayoutParams lp2 = view2.getLayoutParams();
//
//        Log.d(LOG_TAG, "Class of view2: " + view2.getClass().toString());
//        Log.d(LOG_TAG, "Class of layoutParams of view2: " + lp2.getClass().toString());
//        Log.d(LOG_TAG, "Text of view2: " + ((TextView) view2).getText());

        ///////////////////////////////////////////////////////////////////////////////////

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.Lesson30_Layout_LL);
        View view1 = ltInflater.inflate(R.layout.text_view_lesson30, linLayout, true);
        ViewGroup.LayoutParams lp1 = view1.getLayoutParams();

        Log.d(LOG_TAG, "Class of view1: " + view1.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view1: " + lp1.getClass().toString());

        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.Lesson30_Layout_RL);
        View view2 = ltInflater.inflate(R.layout.text_view_lesson30, relLayout, true);
        ViewGroup.LayoutParams lp2 = view2.getLayoutParams();

        Log.d(LOG_TAG, "Class of view2: " + view2.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view2: " + lp2.getClass().toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson30_ArrowBackWrapLL:
                startActivity(new Intent(Lesson30_LayoutInflater.this, Main_Activity.class));
                break;
        }
    }
}