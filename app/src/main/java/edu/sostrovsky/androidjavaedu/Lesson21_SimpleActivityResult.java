package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson21_SimpleActivityResult  extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private TextView mNameTV;
    private Button mNameBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson21);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson21_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mNameTV  = (TextView) findViewById(R.id.Lesson21_Name_TV);

        mNameBTN = (Button) findViewById(R.id.Lesson21_Name_BTN);
        mNameBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson21_ArrowBackWrapLL:
                                                startActivity(new Intent(Lesson21_SimpleActivityResult.this, Main_Activity.class));
                                                break;

            case R.id.Lesson21_Name_BTN:        startActivityForResult(new Intent(this, Lesson21_NameActivity.class), 1);
                                                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(data == null)
            return;

        String name = data.getStringExtra("name");

        mNameTV.setText(getResources().getString(R.string.lesson21_your_name_is_text) + " " + name);
    }
}