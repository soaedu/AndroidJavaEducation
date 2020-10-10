package edu.sostrovsky.androidjavaedu;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 17.12.2016.
 */
public class Lesson62_PreferencesActivity extends PreferenceActivity {

    private CheckBoxPreference mChb3;
    private PreferenceCategory mCategory2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.preferences_lesson62);

        mChb3 = (CheckBoxPreference) findPreference(getResources().getString(R.string.checkbox3_text));

        mCategory2  = (PreferenceCategory) findPreference(getResources().getString(R.string.category2_text));
        mCategory2.setEnabled(mChb3.isChecked());

        mChb3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                mCategory2.setEnabled(mChb3.isChecked());
                return false;
            }
        });
    }
}
