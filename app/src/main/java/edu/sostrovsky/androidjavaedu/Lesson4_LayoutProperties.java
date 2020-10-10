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
public class Lesson4_LayoutProperties   extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button weightBtn;
    private Button gravityBtn;
    private Button marginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson4_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        weightBtn = (Button) findViewById(R.id.Lesson4_Weight_BTN);
        weightBtn.setOnClickListener(this);

        gravityBtn = (Button) findViewById(R.id.Lesson4_Gravity_BTN);
        gravityBtn.setOnClickListener(this);

        marginBtn = (Button) findViewById(R.id.Lesson4_Margin_BTN);
        marginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson4_ArrowBackWrapLL:      startActivity(new Intent(Lesson4_LayoutProperties.this, Main_Activity.class));
                                                    break;
            case R.id.Lesson4_Weight_BTN:           startActivity(new Intent(Lesson4_LayoutProperties.this, Lesson4_LayoutWeight.class));
                                                    break;
            case R.id.Lesson4_Gravity_BTN:          startActivity(new Intent(Lesson4_LayoutProperties.this, Lesson4_LayoutGravity.class));
                                                    break;
            case R.id.Lesson4_Margin_BTN:           startActivity(new Intent(Lesson4_LayoutProperties.this, Lesson4_LayoutMargin.class));
                                                    break;
        }
    }
}
