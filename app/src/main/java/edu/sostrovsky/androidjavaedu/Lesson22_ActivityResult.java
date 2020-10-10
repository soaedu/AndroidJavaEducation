package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson22_ActivityResult    extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private TextView mTextTV;

    private Button mColorBTN;
    private Button mAlignBTN;

    private final int REQUEST_CODE_COLOR = 1;
    private final int REQUEST_CODE_ALIGN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson22);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson22_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mTextTV  = (TextView) findViewById(R.id.Lesson22_Text_TV);

        mColorBTN = (Button) findViewById(R.id.Lesson22_Color_BTN);
        mColorBTN.setOnClickListener(this);

        mAlignBTN = (Button) findViewById(R.id.Lesson22_Align_BTN);
        mAlignBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson22_ArrowBackWrapLL:
                                                startActivity(new Intent(Lesson22_ActivityResult.this, Main_Activity.class));
                                                break;

            case R.id.Lesson22_Color_BTN:       startActivityForResult(new Intent(this, Lesson22_ColorActivity.class), REQUEST_CODE_COLOR);
                                                break;

            case R.id.Lesson22_Align_BTN:       startActivityForResult(new Intent(this, Lesson22_AlignActivity.class), REQUEST_CODE_ALIGN);
                                                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode);

        // если пришло ОК
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_COLOR:
                                        int color = data.getIntExtra("color", Color.WHITE);
                                        mTextTV.setTextColor(color);
                                        break;
                case REQUEST_CODE_ALIGN:
                                        int align = data.getIntExtra("alignment", Gravity.LEFT);
                                        mTextTV.setGravity(align);
                                        break;
            }
          // если вернулось не ОК
        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }
    }
}