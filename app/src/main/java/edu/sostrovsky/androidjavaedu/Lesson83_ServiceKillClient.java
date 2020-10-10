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
public class Lesson83_ServiceKillClient extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson83);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson83_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson83_ArrowBackWrapLL:
                startActivity(new Intent(Lesson83_ServiceKillClient.this, Main_Activity.class));
                break;
            case R.id.Lesson83_Start_Btn:
                // startService(new Intent("com.example.os1.start_android_education.MyService").putExtra("name", "value"));
                break;
            default:
                break;
        }
    }


}