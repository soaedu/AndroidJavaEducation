package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 17.12.2016.
 */
public class Lesson63_PreferencesCode   extends Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson63);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson63_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson63_ArrowBackWrapLL:
                startActivity(new Intent(Lesson63_PreferencesCode.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(0, 1, 0, "Preferences");

        mi.setIntent(new Intent(this, Lesson63_PreferencesActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
}
