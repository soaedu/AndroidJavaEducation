package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 16.12.2016.
 */
public class Lesson58_SecondActivity    extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson58_second_activity);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson58_SecondActivity_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        StringBuilder sb = new StringBuilder("");
        sb.append(getResources().getString(R.string.lesson58_parcel_object_send_title));
        sb.append(" -> ");
        sb.append(getResources().getString(R.string.second_activity_text));

        TextView mTitleTV = (TextView) findViewById(R.id.Lesson58_ShowTitle_TV);
        mTitleTV.setText(sb.toString());

        ////////////////////////////////////////////////////////////////////////////////////////

        Log.d(LOG_TAG, "getParcelableExtra");

        Lesson58_MyObject myObj = (Lesson58_MyObject) getIntent().getParcelableExtra(Lesson58_MyObject.class.getCanonicalName());

        Log.d(LOG_TAG, "myObj: " + myObj.s + ", " + myObj.i);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson58_SecondActivity_ArrowBackWrapLL:
                startActivity(new Intent(Lesson58_SecondActivity.this, Lesson58_Parcelable.class));
                break;
        }
    }
}