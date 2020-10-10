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
public class Lesson16_TwoActivities extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mOpenSecondActivityBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson16);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson16_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mOpenSecondActivityBTN = (Button) findViewById(R.id.Lesson16_OpenSecondActivity_BTN);
        mOpenSecondActivityBTN.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson16_ArrowBackWrapLL:         startActivity(new Intent(Lesson16_TwoActivities.this, Main_Activity.class));
                                                        break;
            case R.id.Lesson16_OpenSecondActivity_BTN:  startActivity(new Intent(Lesson16_TwoActivities.this, Lesson16_SecondActivity.class));
                                                        break;
        }
    }
}