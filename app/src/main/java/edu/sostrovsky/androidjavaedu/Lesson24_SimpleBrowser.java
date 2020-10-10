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
 * Created by OS1 on 06.12.2016.
 */
public class Lesson24_SimpleBrowser extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mWebBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson24);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson24_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mWebBTN = (Button) findViewById(R.id.Lesson24_Web_BTN);
        mWebBTN.setOnClickListener(this);

//        findViewById(R.id.Lesson24_Web_BTN).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ya.ru")));
//            }
//        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson24_ArrowBackWrapLL:
                                                startActivity(new Intent(Lesson24_SimpleBrowser.this, Main_Activity.class));
                                                break;

            case R.id.Lesson24_Web_BTN:         startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")));
                                                break;

        }
    }
}