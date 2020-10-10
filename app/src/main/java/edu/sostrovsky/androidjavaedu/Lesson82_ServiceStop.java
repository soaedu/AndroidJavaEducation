package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 21.12.2016.
 */
public class Lesson82_ServiceStop   extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson82);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson82_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson82_ArrowBackWrapLL:
                startActivity(new Intent(Lesson82_ServiceStop.this, Main_Activity.class));
                break;
            case R.id.Lesson82_Start_Btn:
                startService(new Intent(this, Lesson82_MyService.class).putExtra("time", 7));
                startService(new Intent(this, Lesson82_MyService.class).putExtra("time", 2));
                startService(new Intent(this, Lesson82_MyService.class).putExtra("time", 4));
                break;
            default:
                break;
        }
    }


}