package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson21_NameActivity  extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private EditText mNameET;
    private Button mOkBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson21_name_activity);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson21_NameActivity_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        StringBuilder sb = new StringBuilder("");
        sb.append(getResources().getString(R.string.lesson21_start_activity_for_result_title));
        sb.append(" -> ");
        sb.append(getResources().getString(R.string.second_activity_text));

        TextView title = (TextView) findViewById(R.id.Lesson21_NameActivityTitle_TV);
        title.setText(sb.toString());

        /////////////////////////////////////////////////////////////////////////////////////

        mNameET = (EditText) findViewById(R.id.Lesson21_EnterName_ET);

        mOkBTN  = (Button) findViewById(R.id.Lesson21_OK_BTN);
        mOkBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson21_NameActivity_ArrowBackWrapLL:    startActivity(new Intent(Lesson21_NameActivity.this, Lesson21_SimpleActivityResult.class));
                                                                break;

            case R.id.Lesson21_OK_BTN:                          Intent intent = new Intent();
                                                                intent.putExtra("name", mNameET.getText().toString());
                                                                setResult(RESULT_OK, intent);
                                                                finish();
        }
    }
}