package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 29.12.2016.
 */

public class Lesson94_Fragment2 extends Fragment {

    public interface onSomeEventListener {
        public void someEvent(String str);
    }

    onSomeEventListener mSomeEventListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mSomeEventListener = (onSomeEventListener) activity;
        } catch(ClassCastException ex) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    private final String LOG_TAG = "myLogs";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment2_lesson94, null);

        Button button = (Button) v.findViewById(R.id.Lesson94_Fragment2_Btn);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Log.d(LOG_TAG, "Button click in Fragment2");
                mSomeEventListener.someEvent("Test text to Fragment1");
            }
        });

        return v;
    }
}
