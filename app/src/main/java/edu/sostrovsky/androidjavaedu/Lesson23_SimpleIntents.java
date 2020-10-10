package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson23_SimpleIntents extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mWebBTN;
    private Button mMapBTN;
    private Button mCallBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson23);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson23_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mWebBTN = (Button) findViewById(R.id.Lesson23_Web_BTN);
        mWebBTN.setOnClickListener(this);

        mMapBTN = (Button) findViewById(R.id.Lesson23_Map_BTN);
        mMapBTN.setOnClickListener(this);

        mCallBTN = (Button) findViewById(R.id.Lesson23_Call_BTN);
        mCallBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson23_ArrowBackWrapLL:
                                                startActivity(new Intent(Lesson23_SimpleIntents.this, Main_Activity.class));
                                                break;

            case R.id.Lesson23_Web_BTN:         startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com")));
                                                break;

            case R.id.Lesson23_Map_BTN:         startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("geo:55.754283,37.62002")));
                                                break;

            case R.id.Lesson23_Call_BTN:        startActivity(new Intent().setAction(Intent.ACTION_DIAL).setData(Uri.parse("tel:12345")));
                                                break;
        }
    }
}