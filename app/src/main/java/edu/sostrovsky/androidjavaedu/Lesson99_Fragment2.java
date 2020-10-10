package edu.sostrovsky.androidjavaedu;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 02.01.2017.
 */

public class Lesson99_Fragment2 extends PreferenceFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences2_fragment_lesson99);
    }
}
