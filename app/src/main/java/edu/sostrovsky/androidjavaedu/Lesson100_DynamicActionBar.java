package edu.sostrovsky.androidjavaedu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 02.01.2017.
 */
public class Lesson100_DynamicActionBar extends AppCompatActivity
        implements View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    private final int MENU_ID = 1;

    private CheckBox mAddDeleteChb;
    private CheckBox mShowHideChb;

    private Fragment mFragment1;
    private Fragment mFragment2;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson100);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson100_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ////////////////////////////////////////////////////////////////////////////////////

        mAddDeleteChb = (CheckBox) findViewById(R.id.Lesson100_AddDel_Chb);
        mShowHideChb = (CheckBox) findViewById(R.id.Lesson100_ShowHide_Chb);

        mFragment = mFragment1 = new Lesson100_Fragment1();
        mFragment2 = new Lesson100_Fragment2();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lesson100, menu);
        menu.setGroupVisible(R.id.menu_lesson100_Visible_Grp, mShowHideChb.isChecked());
        if (mAddDeleteChb.isChecked()) {
            menu.add(0, MENU_ID, 0, R.string.item1_text)
                    .setIcon(android.R.drawable.ic_delete)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        } else {
            menu.removeItem(MENU_ID);
        }
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson100_ArrowBackWrapLL:
                startActivity(new Intent(Lesson100_DynamicActionBar.this, Main_Activity.class));
                break;
            case R.id.Lesson100_AddDel_Chb:
            case R.id.Lesson100_ShowHide_Chb:
                invalidateOptionsMenu();
                break;
            case R.id.Lesson100_Fragment_Btn:
                mFragment = (mFragment == mFragment1) ? mFragment2 : mFragment1;
                getFragmentManager().beginTransaction().replace(R.id.Lesson100_Container_FL, mFragment).commit();
                break;
            default:
                break;
        }
    }
}