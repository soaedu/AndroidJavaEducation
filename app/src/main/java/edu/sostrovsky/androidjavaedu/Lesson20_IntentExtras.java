package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson20_IntentExtras  extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private EditText mFNameET;
    private EditText mLNameET;

    private Button mSubmitBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson20);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson20_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mFNameET = (EditText) findViewById(R.id.Lesson20_FName_ET);

        mLNameET = (EditText) findViewById(R.id.Lesson20_LName_ET);

        mSubmitBTN = (Button) findViewById(R.id.Lesson20_Submit_BTN);
        mSubmitBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson20_ArrowBackWrapLL: startActivity(new Intent(Lesson20_IntentExtras.this, Main_Activity.class));
                                                break;
            case R.id.Lesson20_Submit_BTN:
                                                Intent intent = new Intent(this, Lesson20_ViewActivity.class);
                                                intent.putExtra("fname", mFNameET.getText().toString());
                                                intent.putExtra("lname", mLNameET.getText().toString());
                                                startActivity(intent);
                                                break;
        }
    }
}