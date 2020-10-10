package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson22_ColorActivity extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mRedBTN;
    private Button mGreenBTN;
    private Button mBlueBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson22_color_activity);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson22_ColorActivity_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        StringBuilder sb = new StringBuilder("");
        sb.append(getResources().getString(R.string.lesson22_on_activity_result_title));
        sb.append(" -> ");
        sb.append("Color Activity");

        TextView title = (TextView) findViewById(R.id.Lesson22_ColorActivityTitle_TV);
        title.setText(sb.toString());

        /////////////////////////////////////////////////////////////////////////////////////

        mRedBTN  = (Button) findViewById(R.id.Lesson22_Red_BTN);
        mRedBTN.setOnClickListener(this);

        mGreenBTN  = (Button) findViewById(R.id.Lesson22_Green_BTN);
        mGreenBTN.setOnClickListener(this);

        mBlueBTN  = (Button) findViewById(R.id.Lesson22_Blue_BTN);
        mBlueBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson22_ColorActivity_ArrowBackWrapLL:   startActivity(new Intent(Lesson22_ColorActivity.this, Lesson22_ActivityResult.class));
                                                                break;

            case R.id.Lesson22_Red_BTN:                         Intent redIntent = new Intent();
                                                                redIntent.putExtra("color", Color.RED);
                                                                setResult(RESULT_OK, redIntent);
                                                                finish();
                                                                break;

            case R.id.Lesson22_Green_BTN:                       Intent greenIntent = new Intent();
                                                                greenIntent.putExtra("color", Color.GREEN);
                                                                setResult(RESULT_OK, greenIntent);
                                                                finish();
                                                                break;

            case R.id.Lesson22_Blue_BTN:                        Intent blueIntent = new Intent();
                                                                blueIntent.putExtra("color", Color.BLUE);
                                                                setResult(RESULT_OK, blueIntent);
                                                                finish();
                                                                break;
        }
    }
}