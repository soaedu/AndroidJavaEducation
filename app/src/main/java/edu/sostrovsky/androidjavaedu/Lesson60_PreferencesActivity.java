package edu.sostrovsky.androidjavaedu;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 17.12.2016.
 */
public class Lesson60_PreferencesActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.preferences_lesson60);
    }
}
