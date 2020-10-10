package edu.sostrovsky.androidjavaedu;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 30.12.2016.
 */
public class Lesson96_ActionBarNavigation extends AppCompatActivity
        implements View.OnClickListener,
        ActionBar.TabListener {
    // ActionBar.OnNavigationListener {

    private LinearLayout arrowBackWrapLL;

    private androidx.appcompat.app.ActionBar mActionBar;

    private final String LOG_TAG = "myLogs";

    private String[] data = new String[]{"one", "two", "three"};

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson96);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson96_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mActionBar = getSupportActionBar();

        assert mActionBar != null;
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        androidx.appcompat.app.ActionBar.Tab tab = mActionBar.newTab();
        tab.setText(R.string.tab1_text);
        tab.setTabListener((androidx.appcompat.app.ActionBar.TabListener) this);
        mActionBar.addTab(tab);

        tab = mActionBar.newTab();
        tab.setText(R.string.tab2_text);
        tab.setTabListener((androidx.appcompat.app.ActionBar.TabListener) this);
        mActionBar.addTab(tab);

        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);

        // установить свой заголовок
        mActionBar.setTitle("MyTitle");

        // установить свой подзаголовок
        mActionBar.setSubtitle("MySubTitle");

        // скрыть/показать заголовок и подзаголовок
        mActionBar.setDisplayShowTitleEnabled(true);

        // скрыть/показать кнопку Home
        mActionBar.setDisplayShowHomeEnabled(true);

//        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mActionBar.setListNavigationCallbacks(adapter, this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson96_ArrowBackWrapLL:
                startActivity(new Intent(Lesson96_ActionBarNavigation.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lesson95, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true; // обработка нажатия на пункты в ActionBar
    }

//    @Override
//    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
//        Log.d(LOG_TAG, "selected: position = " + itemPosition + ", id = " + itemId + ", " + data[itemPosition]);
//        return false;
//    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        Log.d(LOG_TAG, "reselected tab: " + tab.getText());

        /*
        в методах обработчика мы кодим операции с фрагментами. Нам даже любезно предоставляют
        объект FragmentTransaction для этих целей. При этом хелп предупреждает, что в этих
        методах нам не надо самим вызывать метод commit, а также мы не можем добавлять
        транзакцию в BackStack.
        */
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        Log.d(LOG_TAG, "selected tab: " + tab.getText());

        /*
        в методах обработчика мы кодим операции с фрагментами. Нам даже любезно предоставляют
        объект FragmentTransaction для этих целей. При этом хелп предупреждает, что в этих
        методах нам не надо самим вызывать метод commit, а также мы не можем добавлять
        транзакцию в BackStack.
        */
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        Log.d(LOG_TAG, "unselected tab: " + tab.getText());

        /*
        в методах обработчика мы кодим операции с фрагментами. Нам даже любезно предоставляют
        объект FragmentTransaction для этих целей. При этом хелп предупреждает, что в этих
        методах нам не надо самим вызывать метод commit, а также мы не можем добавлять
        транзакцию в BackStack.
        */
    }
}