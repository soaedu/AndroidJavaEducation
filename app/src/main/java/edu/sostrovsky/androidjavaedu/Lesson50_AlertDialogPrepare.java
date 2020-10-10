package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by OS1 on 14.12.2016.
 */
public class Lesson50_AlertDialogPrepare extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mBtn;

    private final int DIALOG = 1;

    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    private final static String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson50);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson50_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mBtn = (Button) findViewById(R.id.Lesson50_BTN);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson50_ArrowBackWrapLL:
                startActivity(new Intent(Lesson50_AlertDialogPrepare.this, Main_Activity.class));
                break;

            case R.id.Lesson50_BTN:
                showDialog(DIALOG);
                break;
        }
    }

    protected Dialog onCreateDialog(int id) {
        Log.d(LOG_TAG, "onCreateDialog");

        if (id == DIALOG) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(R.string.current_time_text);
            adb.setMessage(sdf.format(new Date(System.currentTimeMillis())));
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);

        Log.d(LOG_TAG, "onPrepareDialog");

        if (id == DIALOG) {
            ((AlertDialog)dialog).setMessage(sdf.format(new Date(System.currentTimeMillis())));
        }
    }
}