package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson25_SharedPreferences extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private EditText mTextET;
    private Button mSaveBTN, mLoadBTN;

    private SharedPreferences sPref;

    private final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson25);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson25_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mTextET = (EditText) findViewById(R.id.Lesson25_Text_ET);

        mSaveBTN = (Button) findViewById(R.id.Lesson25_Save_BTN);
        mSaveBTN.setOnClickListener(this);

        mLoadBTN = (Button) findViewById(R.id.Lesson25_Load_BTN);
        mLoadBTN.setOnClickListener(this);

        loadText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson25_ArrowBackWrapLL:
                                                startActivity(new Intent(Lesson25_SharedPreferences.this, Main_Activity.class));
                                                break;

            case R.id.Lesson25_Save_BTN:        saveText();
                                                break;

            case R.id.Lesson25_Load_BTN:        loadText();
                                                break;
            default:
                break;
        }
    }

    void saveText() {
        // sPref = getPreferences(MODE_PRIVATE);
        sPref = getSharedPreferences("lesson25_file_name",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, mTextET.getText().toString());
        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    void loadText() {
        // sPref = getPreferences(MODE_PRIVATE);
        sPref = getSharedPreferences("lesson25_file_name",MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        mTextET.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }
}