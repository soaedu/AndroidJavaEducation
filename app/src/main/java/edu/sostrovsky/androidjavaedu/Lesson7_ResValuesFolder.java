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
public class Lesson7_ResValuesFolder    extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private LinearLayout bottomLL;
    private TextView bottomTV;
    private Button bottomBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson7);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson7_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        bottomLL    = (LinearLayout) findViewById(R.id.Lesson7_Bottom_LL);
        bottomLL.setBackgroundResource(R.color.bottom_ll_green);

        bottomTV    = (TextView) findViewById(R.id.Lesson7_Bottom_TV);
        bottomTV.setText(R.string.lesson7_bottom_text_view_text);

        bottomBTN   = (Button) findViewById(R.id.Lesson7_Bottom_BTN);
        bottomBTN.setText(R.string.lesson7_bottom_btn_text);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson7_ArrowBackWrapLL:  startActivity(new Intent(Lesson7_ResValuesFolder.this, Main_Activity.class));
                                                break;
        }
    }
}