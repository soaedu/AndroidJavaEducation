package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 14.12.2016.
 */
public class Lesson48_DatePickerDialog  extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private int DIALOG_DATE = 1;
    private int myYear = 2011;
    private int myMonth = 02;
    private int myDay = 03;

    private TextView mDateTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson48);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson48_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mDateTV = (TextView) findViewById(R.id.Lesson48_Date_TV);
        mDateTV.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson48_ArrowBackWrapLL:
                startActivity(new Intent(Lesson48_DatePickerDialog.this, Main_Activity.class));
                break;
            case R.id.Lesson48_Date_TV:
                showDialog(DIALOG_DATE);
                break;


        }
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }

        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            mDateTV.setText("Today is " + myDay + "/" + myMonth + "/" + myYear);
        }
    };
}