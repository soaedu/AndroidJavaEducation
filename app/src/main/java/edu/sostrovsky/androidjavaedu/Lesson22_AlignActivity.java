package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson22_AlignActivity extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mLeftBTN;
    private Button mCenterBTN;
    private Button mRightBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson22_align_activity);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson22_AlignActivity_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        StringBuilder sb = new StringBuilder("");
        sb.append(getResources().getString(R.string.lesson22_on_activity_result_title));
        sb.append(" -> ");
        sb.append("Align Activity");

        TextView title = (TextView) findViewById(R.id.Lesson22_AlignActivityTitle_TV);
        title.setText(sb.toString());

        /////////////////////////////////////////////////////////////////////////////////////

        mLeftBTN  = (Button) findViewById(R.id.Lesson22_Left_BTN);
        mLeftBTN.setOnClickListener(this);

        mCenterBTN  = (Button) findViewById(R.id.Lesson22_Center_BTN);
        mCenterBTN.setOnClickListener(this);

        mRightBTN  = (Button) findViewById(R.id.Lesson22_Right_BTN);
        mRightBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson22_AlignActivity_ArrowBackWrapLL:   startActivity(new Intent(Lesson22_AlignActivity.this, Lesson22_ActivityResult.class));
                                                                break;

            case R.id.Lesson22_Left_BTN:                        Intent leftIntent = new Intent();
                                                                leftIntent.putExtra("alignment", Gravity.LEFT);
                                                                setResult(RESULT_OK, leftIntent);
                                                                finish();
                                                                break;

            case R.id.Lesson22_Center_BTN:                      Intent centerIntent = new Intent();
                                                                centerIntent.putExtra("alignment", Gravity.CENTER);
                                                                setResult(RESULT_OK, centerIntent);
                                                                finish();
                                                                break;

            case R.id.Lesson22_Right_BTN:                       Intent rightIntent = new Intent();
                                                                rightIntent.putExtra("alignment", Gravity.RIGHT);
                                                                setResult(RESULT_OK, rightIntent);
                                                                finish();
                                                                break;
        }
    }
}