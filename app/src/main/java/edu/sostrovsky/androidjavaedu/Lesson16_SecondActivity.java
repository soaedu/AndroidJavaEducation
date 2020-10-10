package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson16_SecondActivity    extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson16_second_activity);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson16_SecondActivity_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        StringBuilder sb = new StringBuilder("");
        // sb.append(getResources().getString(R.string.layout_types_title));
        sb.append(getResources().getString(R.string.lesson16_two_activities_title));
        sb.append(" -> ");
        sb.append(getResources().getString(R.string.second_activity_text));

        TextView title = (TextView) findViewById(R.id.Lesson16_SecondActivityTitle_TV);
        title.setText(sb.toString());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson16_SecondActivity_ArrowBackWrapLL:  startActivity(new Intent(Lesson16_SecondActivity.this, Lesson16_TwoActivities.class));
                                                                break;
        }
    }
}
