package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 14.12.2016.
 */
public class Lesson47_TimePickerDialog  extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private int DIALOG_TIME = 1;
    private int myHour = 14;
    private int myMinute = 35;

    private TextView mTimeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson47);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson47_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mTimeTV = (TextView) findViewById(R.id.Lesson47_Time_TV);
        mTimeTV.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson47_ArrowBackWrapLL:
                startActivity(new Intent(Lesson47_TimePickerDialog.this, Main_Activity.class));
                break;
            case R.id.Lesson47_Time_TV:
                showDialog(DIALOG_TIME);
                break;


        }
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return tpd;
        }

        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            mTimeTV.setText("Time is " + myHour + " hours " + myMinute + " minutes");
        }
    };


}