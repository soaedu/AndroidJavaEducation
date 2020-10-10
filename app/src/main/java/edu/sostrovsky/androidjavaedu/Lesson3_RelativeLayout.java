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
public class Lesson3_RelativeLayout extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3_relative_layout);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson3_RelativeLayout_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        StringBuilder sb = new StringBuilder("");
        sb.append(getResources().getString(R.string.layout_types_title));
        sb.append(" -> ");
        sb.append(getResources().getString(R.string.relative_layout_text));

        TextView title = (TextView) findViewById(R.id.Lesson3_RelativeLayoutTitle_TV);
        title.setText(sb.toString());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson3_RelativeLayout_ArrowBackWrapLL:   startActivity(new Intent(Lesson3_RelativeLayout.this, Lesson3_Layouts.class));
                                                                break;
        }
    }
}