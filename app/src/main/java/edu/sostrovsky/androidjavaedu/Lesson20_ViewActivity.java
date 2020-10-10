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
public class Lesson20_ViewActivity  extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private TextView mInfoTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson20_view_activity);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson20_ViewActivity_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        StringBuilder sb = new StringBuilder("");
        sb.append(getResources().getString(R.string.lesson20_extras_title));
        sb.append(" -> ");
        sb.append(getResources().getString(R.string.second_activity_text));

        TextView title = (TextView) findViewById(R.id.Lesson20_ViewActivityTitle_TV);
        title.setText(sb.toString());

        /////////////////////////////////////////////////////////////////////////////////////

        Intent intent = getIntent();

        String fName = intent.getStringExtra("fname");
        String lName = intent.getStringExtra("lname");

        mInfoTV = (TextView) findViewById(R.id.Lesson20_Info_TV);
        mInfoTV.setText("Your name is: " + fName + " " + lName);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson20_ViewActivity_ArrowBackWrapLL:    startActivity(new Intent(Lesson20_ViewActivity.this, Lesson20_IntentExtras.class));
                                                                break;
        }
    }
}