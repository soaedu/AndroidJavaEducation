package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson13_DynamicLayout3    extends     Activity
                                        implements  View.OnClickListener,
                                                    OnSeekBarChangeListener {

    private LinearLayout arrowBackWrapLL;

    private SeekBar weightSB;

    private Button leftBTN;
    private Button rightBTN;

    private LinearLayout.LayoutParams lParams1;
    private LinearLayout.LayoutParams lParams2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson13);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson13_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        weightSB = (SeekBar) findViewById(R.id.Lesson13_Weight_SB);
        weightSB.setOnSeekBarChangeListener(this);

        leftBTN  = (Button) findViewById(R.id.Lesson13_Left_BTN);
        lParams1 = (LinearLayout.LayoutParams) leftBTN.getLayoutParams();

        rightBTN = (Button) findViewById(R.id.Lesson13_Right_BTN);
        lParams2 = (LinearLayout.LayoutParams) rightBTN.getLayoutParams();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson13_ArrowBackWrapLL: startActivity(new Intent(Lesson13_DynamicLayout3.this, Main_Activity.class));
                                                break;
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        int leftValue  = progress;
        int rightValue = seekBar.getMax() - progress;

        // настраиваем вес
        lParams1.weight = leftValue;
        leftBTN.requestLayout();

        lParams2.weight = rightValue;
        rightBTN.requestLayout();

        // в текст кнопок пишем значения переменных
        // leftBTN.setText(String.valueOf(leftValue));
        // rightBTN.setText(String.valueOf(rightValue));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}