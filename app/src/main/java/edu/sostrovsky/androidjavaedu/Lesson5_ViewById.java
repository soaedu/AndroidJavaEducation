package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson5_ViewById   extends     Activity
                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private TextView myTextTV;

    private Button myButtonBTN;

    private CheckBox myCheckBoxCHB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson5);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson5_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        myTextTV = (TextView) findViewById(R.id.Lesson5_MyText_TV);
        myTextTV.setText("New text in TextView");

        myButtonBTN = (Button) findViewById(R.id.Lesson5_MyButton_BTN);
        myButtonBTN.setText("My button");
        myButtonBTN.setEnabled(false);

        myCheckBoxCHB = (CheckBox) findViewById(R.id.Lesson5_CheckBox_CHB);
        myCheckBoxCHB.setChecked(true);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson5_ArrowBackWrapLL:      startActivity(new Intent(Lesson5_ViewById.this, Main_Activity.class));
                                                    break;
        }
    }
}
