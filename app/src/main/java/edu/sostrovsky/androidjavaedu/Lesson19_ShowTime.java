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
public class Lesson19_ShowTime  extends     Activity
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
        sb.append(getResources().getString(R.string.lesson19_show_time_text));

        TextView title = (TextView) findViewById(R.id.Lesson19_ShowTimeTitle_TV);
        title.setText(sb.toString());

        ////////////////////////////////////////////////////////////////////////////////////////

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(new Date(System.currentTimeMillis()));

        TextView tvTime = (TextView) findViewById(R.id.Lesson19_ShowTime_TV);
        tvTime.setText(time);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson19_ShowTime_ArrowBackWrapLL:    startActivity(new Intent(Lesson19_ShowTime.this, Lesson19_IntentFilter.class));
                                                            break;
        }
    }
}