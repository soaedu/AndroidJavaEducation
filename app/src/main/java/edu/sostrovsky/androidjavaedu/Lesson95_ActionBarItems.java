package edu.sostrovsky.androidjavaedu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 30.12.2016.
 */
public class Lesson95_ActionBarItems extends AppCompatActivity
        implements View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson95);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson95_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        // получаем Actionbar
        mActionBar = getSupportActionBar();

        assert mActionBar != null;
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
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson95_ArrowBackWrapLL:
                startActivity(new Intent(Lesson95_ActionBarItems.this, Main_Activity.class));
                break;
            case R.id.Lesson95_Btn:
                if (mActionBar != null)
                    // скрываем Actionbar
                    mActionBar.hide();
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

        switch (item.getItemId()) {

            case android.R.id.home:
                Toast.makeText(this, "Button Home pressed", Toast.LENGTH_SHORT).show();
                break;
        }

        return true; // обработка нажатия на пункты в ActionBar
    }
}