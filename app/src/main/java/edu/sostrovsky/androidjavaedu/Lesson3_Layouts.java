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
public class Lesson3_Layouts    extends     Activity
                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button linearLayoutBtn;
    private Button tableLayoutBtn;
    private Button relativeLayoutBtn;
    private Button absoluteLayoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson3_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        linearLayoutBtn = (Button) findViewById(R.id.Lesson3_LinearLayout_BTN);
        linearLayoutBtn.setOnClickListener(this);

        tableLayoutBtn = (Button) findViewById(R.id.Lesson3_TableLayout_BTN);
        tableLayoutBtn.setOnClickListener(this);

        relativeLayoutBtn = (Button) findViewById(R.id.Lesson3_RelativeLayout_BTN);
        relativeLayoutBtn.setOnClickListener(this);

        absoluteLayoutBtn = (Button) findViewById(R.id.Lesson3_AbsoluteLayout_BTN);
        absoluteLayoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson3_ArrowBackWrapLL:      startActivity(new Intent(Lesson3_Layouts.this, Main_Activity.class));
                                                    break;
            case R.id.Lesson3_LinearLayout_BTN:     startActivity(new Intent(Lesson3_Layouts.this, Lesson3_LinearLayout.class));
                                                    break;
            case R.id.Lesson3_TableLayout_BTN:      startActivity(new Intent(Lesson3_Layouts.this, Lesson3_TableLayout.class));
                                                    break;
            case R.id.Lesson3_RelativeLayout_BTN:   startActivity(new Intent(Lesson3_Layouts.this, Lesson3_RelativeLayout.class));
                                                    break;
            case R.id.Lesson3_AbsoluteLayout_BTN:   startActivity(new Intent(Lesson3_Layouts.this, Lesson3_AbsoluteLayout.class));
                                                    break;

        }
    }
}
