package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson6_OnClickButtons extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private TextView myTextTV;

    private Button okBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson6);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson6_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        myTextTV    = (TextView) findViewById(R.id.Lesson6_TextView_TV);
        myTextTV.setText("New text in TextView");

        okBtn       = (Button) findViewById(R.id.Lesson6_Ok_BTN);
        okBtn.setOnClickListener(this);

        cancelBtn   = (Button) findViewById(R.id.Lesson6_Cancel_BTN);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson6_ArrowBackWrapLL:  startActivity(new Intent(Lesson6_OnClickButtons.this, Main_Activity.class));
                                                break;
            case R.id.Lesson6_Ok_BTN:
                                                myTextTV.setText(getResources().getString(R.string.lesson6_btn_ok_clicked_text));
                                                break;
            case R.id.Lesson6_Cancel_BTN:
                                                myTextTV.setText(getResources().getString(R.string.lesson6_btn_cancel_clicked_text));
                                                break;
        }
    }
}
