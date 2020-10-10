package edu.sostrovsky.androidjavaedu;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 31.12.2016.
 */
public class Lesson98_Dialog1   extends DialogFragment
                                implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Title!");

        View v = inflater.inflate(R.layout.fragment_dialog_lesson98, null);
        v.findViewById(R.id.Lesson98_Yes_Btn).setOnClickListener(this);
        v.findViewById(R.id.Lesson98_No_Btn).setOnClickListener(this);
        v.findViewById(R.id.Lesson98_Maybe_Btn).setOnClickListener(this);

        return v;
    }

    public void onClick(View v) {
        Log.d(LOG_TAG, "Dialog 1: " + ((Button) v).getText());
        dismiss();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 1: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }
}