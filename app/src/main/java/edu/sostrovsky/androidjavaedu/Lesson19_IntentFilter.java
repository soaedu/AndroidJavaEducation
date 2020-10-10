package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson19_IntentFilter  extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mShowTimeBTN;
    private Button mShowDateBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson19);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson19_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mShowTimeBTN = (Button) findViewById(R.id.Lesson19_ShowTime_BTN);
        mShowTimeBTN.setOnClickListener(this);

        mShowDateBTN = (Button) findViewById(R.id.Lesson19_ShowDate_BTN);
        mShowDateBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson19_ArrowBackWrapLL: startActivity(new Intent(Lesson19_IntentFilter.this, Main_Activity.class));
                                                break;
            case R.id.Lesson19_ShowTime_BTN:    startActivity(new Intent("com.example.os1.start_android_education.action.showtime"));
                                                break;
            case R.id.Lesson19_ShowDate_BTN:    startActivity(new Intent("com.example.os1.start_android_education.action.showdate"));
                                                break;
        }
    }
}