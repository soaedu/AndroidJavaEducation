package edu.sostrovsky.androidjavaedu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 31.12.2016.
 */
public class Lesson98_Dialog2   extends     DialogFragment
                                implements  DialogInterface.OnClickListener {

    final String LOG_TAG = "myLogs";

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle("Title!").setPositiveButton(R.string.yes_text, this)
                .setNegativeButton(R.string.no_text, this)
                .setNeutralButton(R.string.maybe_text, this)
                .setMessage(R.string.message_text);
        return adb.create();
    }

    public void onClick(DialogInterface dialog, int which) {
        int i = 0;
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                i = R.string.yes_text;
                break;
            case Dialog.BUTTON_NEGATIVE:
                i = R.string.no_text;
                break;
            case Dialog.BUTTON_NEUTRAL:
                i = R.string.maybe_text;
                break;
        }
        if (i > 0)
            Log.d(LOG_TAG, "Dialog 2: " + getResources().getString(i));
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 2: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 2: onCancel");
    }
}