package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 16.12.2016.
 */
public class Lesson55_AlertDialogOperations extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mBtn;

    private Dialog dialog;

    private final int DIALOG = 1;

    private final static String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson55);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson55_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mBtn = (Button) findViewById(R.id.Lesson55_Btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson55_ArrowBackWrapLL:
                startActivity(new Intent(Lesson55_AlertDialogOperations.this, Main_Activity.class));
                break;
            case R.id.Lesson55_Btn:
                showDialog(DIALOG);

                Handler h = new Handler();

                h.postDelayed(new Runnable() {
                    public void run() {
                        method1();
                    }
                }, 2000);

                h.postDelayed(new Runnable() {
                    public void run() {
                        method2();
                    }
                }, 4000);

                break;
            default:
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        if (id == DIALOG) {
            Log.d(LOG_TAG, "Create");

            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Title");
            adb.setMessage("Message");
            adb.setPositiveButton("OK", null);

            dialog = adb.create();

            // обработчик отображения
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialog) {
                    Log.d(LOG_TAG, "Show");
                }
            });

            // обработчик отмены
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    Log.d(LOG_TAG, "Cancel");
                }
            });

            // обработчик закрытия
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialog) {
                    Log.d(LOG_TAG, "Dismiss");
                }
            });
            return dialog;
        }
        return super.onCreateDialog(id);
    }

    private void method1() {
        // dialog.dismiss();
        // dialog.cancel();
        // dialog.hide();

        // dismissDialog(DIALOG);
        removeDialog(DIALOG);
    }

    private void method2() {
        showDialog(DIALOG);
    }
}