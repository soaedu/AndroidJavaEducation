package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 31.12.2016.
 */
public class Lesson97_ListFragment extends Activity
                                   implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    private String[] data = new String[] { "one", "two", "three" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson97);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson97_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson97_ArrowBackWrapLL:
                startActivity(new Intent(Lesson97_ListFragment.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }
}