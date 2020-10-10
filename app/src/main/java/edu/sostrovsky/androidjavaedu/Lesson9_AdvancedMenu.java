package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson9_AdvancedMenu extends     Activity
                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private CheckBox mAdvancedCHB;

    private TextView mAdvancedTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson9);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson9_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mAdvancedCHB = (CheckBox) findViewById(R.id.Lesson9_AdvancedMenu_CHB);
        mAdvancedTV  = (TextView) findViewById(R.id.Lesson9_AdvancedMenu_TV);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson9_ArrowBackWrapLL:  startActivity(new Intent(Lesson9_AdvancedMenu.this, Main_Activity.class));
                                                break;
        }
    }

    // создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 1, 0, "create");
        menu.add(0, 1, 0, "add");
        menu.add(0, 2, 0, "edit");
        menu.add(0, 3, 3, "delete");
        menu.add(1, 4, 1, "copy");
        menu.add(1, 5, 2, "paste");
        menu.add(1, 6, 4, "exit");

        // getMenuInflater().inflate(R.menu.menu_lesson9, menu);

        // true - меню показывать, false - меню не показывать
        // по-умолчанию это true
        return super.onCreateOptionsMenu(menu);
    }

    // обновление меню
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        // пункты меню с ID = 1 видны, если в Checkbox стоит галка
        menu.setGroupVisible(1, mAdvancedCHB.isChecked());

        return super.onPrepareOptionsMenu(menu);
    }

    // обработка нажатий
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        StringBuilder sb = new StringBuilder();

        sb.append("Item menu");
        sb.append("\r\n groupId: " +String.valueOf(item.getGroupId()));
        sb.append("\r\n itemId: " +String.valueOf(item.getItemId()));
        sb.append("\r\n order: " +String.valueOf(item.getOrder()));
        sb.append("\r\n title: " +item.getTitle());

        mAdvancedTV.setText(sb.toString());

        return super.onOptionsItemSelected(item);
    }
}