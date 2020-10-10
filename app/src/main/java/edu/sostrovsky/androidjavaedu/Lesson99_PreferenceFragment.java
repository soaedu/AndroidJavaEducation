package edu.sostrovsky.androidjavaedu;

import android.preference.PreferenceActivity;

import com.sostrovsky.androidjavaedu.R;

import java.util.List;

/**
 * Created by OS1 on 02.01.2017.
 */
public class Lesson99_PreferenceFragment extends PreferenceActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        getFragmentManager().beginTransaction().replace(android.R.id.content, new Lesson99_Fragment1()).commit();
//    }


    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preferences_header_lesson99, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return true;
    }
}