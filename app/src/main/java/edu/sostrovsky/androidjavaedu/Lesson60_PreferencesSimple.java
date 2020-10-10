package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 17.12.2016.
 */
public class Lesson60_PreferencesSimple extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private TextView mInfoTV;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson60);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson60_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////

        mInfoTV = (TextView) findViewById(R.id.Lesson60_Info_TV);

        // получаем SharedPreferences, которое работает с файлом настроек
        sp = PreferenceManager.getDefaultSharedPreferences(this);

        // полная очистка настроек
        // sp.edit().clear().commit();
    }

    protected void onResume() {
        Boolean notify = sp.getBoolean(getResources().getString(R.string.notifications_text), false);
        String address = sp.getString(getResources().getString(R.string.address_text), "");

        String text = "Notifications are " + ((notify) ? "enabled, address = " + address : "disabled");
        mInfoTV.setText(text);

        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(0, 1, 0, "Preferences");
        mi.setIntent(new Intent(this, Lesson60_PreferencesActivity.class));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson60_ArrowBackWrapLL:
                startActivity(new Intent(Lesson60_PreferencesSimple.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }
}