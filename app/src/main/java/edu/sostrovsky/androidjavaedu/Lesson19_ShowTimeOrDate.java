package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson19_ShowTimeOrDate    extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson19_show_time);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson19_ShowTime_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        StringBuilder sb = new StringBuilder("");
        sb.append(getResources().getString(R.string.lesson19_intent_filter_title));
        sb.append(" -> ");
        sb.append(getResources().getString(R.string.lesson19_show_time_or_date_text));

        TextView title = (TextView) findViewById(R.id.Lesson19_ShowTimeTitle_TV);
        title.setText(sb.toString());

        ////////////////////////////////////////////////////////////////////////////////////////

        // получаем Intent, который вызывал это Activity
        Intent intent = getIntent();

        // читаем из него action
        String action = intent.getAction();

        String format = "", textInfo = "";

        // в зависимости от action заполняем переменные
        if (action.equals("com.example.os1.start_android_education.action.showtime")) {
            format      = "HH:mm:ss";
            textInfo    = "Time: ";
        }
        else if (action.equals("com.example.os1.start_android_education.action.showdate")) {
            format      = "dd.MM.yyyy";
            textInfo    = "Date: ";
        }

        // в зависимости от содержимого переменной format
        // получаем дату или время в переменную datetime
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String datetime = sdf.format(new Date(System.currentTimeMillis()));

        TextView tvDate = (TextView) findViewById(R.id.Lesson19_ShowTime_TV);
        tvDate.setText(textInfo + datetime);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson19_ShowTime_ArrowBackWrapLL:    startActivity(new Intent(Lesson19_ShowTimeOrDate.this, Lesson19_IntentFilter.class));
                                                            break;
        }
    }
}