package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson1_BasicViews     extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson1_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson1_ArrowBackWrapLL:  startActivity(new Intent(Lesson1_BasicViews.this, Main_Activity.class));
                                                break;
        }
    }
}