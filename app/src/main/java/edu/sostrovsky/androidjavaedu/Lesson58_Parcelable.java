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
 * Created by OS1 on 16.12.2016.
 */
public class Lesson58_Parcelable extends     Activity
                                 implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mBtn;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson58);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson58_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mBtn = (Button) findViewById(R.id.Lesson58_Btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson58_ArrowBackWrapLL:
                startActivity(new Intent(Lesson58_Parcelable.this, Main_Activity.class));
                break;
            case R.id.Lesson58_Btn:
                Lesson58_MyObject myObj = new Lesson58_MyObject("text", 1);

                Intent intent = new Intent(this, Lesson58_SecondActivity.class);
                intent.putExtra(Lesson58_MyObject.class.getCanonicalName(), myObj);

                Log.d(LOG_TAG, "startActivity");

                startActivity(intent);
        }
    }
}