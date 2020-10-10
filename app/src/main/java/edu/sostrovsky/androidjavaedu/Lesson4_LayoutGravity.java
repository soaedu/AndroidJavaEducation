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
public class Lesson4_LayoutGravity extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4_layout_gravity);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson4_LayoutGravity_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        StringBuilder sb = new StringBuilder("");
        sb.append(getResources().getString(R.string.lesson4_layout_properties_text));
        sb.append(" -> ");
        sb.append("Gravity");

        TextView title = (TextView) findViewById(R.id.Lesson4_LayoutGravityTitle_TV);
        title.setText(sb.toString());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson4_LayoutGravity_ArrowBackWrapLL:    startActivity(new Intent(Lesson4_LayoutGravity.this, Lesson4_LayoutProperties.class));
                                                                break;
        }
    }
}
