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
public class Lesson4_LayoutWeight   extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4_layout_weight);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson4_LayoutWeight_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        StringBuilder sb = new StringBuilder("");
        sb.append(getResources().getString(R.string.lesson4_layout_properties_text));
        sb.append(" -> ");
        sb.append("Weight");

        TextView title = (TextView) findViewById(R.id.Lesson4_LayoutWeightTitle_TV);
        title.setText(sb.toString());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson4_LayoutWeight_ArrowBackWrapLL: startActivity(new Intent(Lesson4_LayoutWeight.this, Lesson4_LayoutProperties.class));
                                                            break;
        }
    }
}
