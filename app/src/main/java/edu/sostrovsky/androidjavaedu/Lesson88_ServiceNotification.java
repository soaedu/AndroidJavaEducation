package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 25.12.2016.
 */
public class Lesson88_ServiceNotification   extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    public final static String FILE_NAME = "filename";

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson88);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson88_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////

        TextView tv = (TextView) findViewById(R.id.Lesson88_TV);

        Intent intent = getIntent();

        String fileName = intent.getStringExtra(FILE_NAME);

        if (!TextUtils.isEmpty(fileName))
            tv.setText(fileName);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson88_ArrowBackWrapLL:
                startActivity(new Intent(Lesson88_ServiceNotification.this, Main_Activity.class));
                break;
            case R.id.Lesson88_Start_Btn:
                startService(new Intent(this, Lesson88_MyService.class));
                break;
            case R.id.Lesson88_Stop_Btn:
                stopService(new Intent(this, Lesson88_MyService.class));
                break;
            default:
                break;
        }
    }
}