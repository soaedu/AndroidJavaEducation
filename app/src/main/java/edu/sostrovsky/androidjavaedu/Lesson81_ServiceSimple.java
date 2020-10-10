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
public class Lesson81_ServiceSimple extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson81);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson81_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson81_ArrowBackWrapLL:
                startActivity(new Intent(Lesson81_ServiceSimple.this, Main_Activity.class));
                break;
            case R.id.Lesson81_Start_Btn:
                startService(new Intent(this, Lesson81_MyService.class));
                break;
            case R.id.Lesson81_Stop_Btn:
                stopService(new Intent(this, Lesson81_MyService.class));
                break;
            default:
                break;
        }
    }


}