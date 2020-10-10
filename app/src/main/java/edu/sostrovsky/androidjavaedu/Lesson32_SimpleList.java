package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson32_SimpleList    extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь", "Анна", "Денис", "Андрей" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson32);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson32_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        // находим список
        ListView lvMain = (ListView) findViewById(R.id.Lesson31_Main_LV);

        // создаем адаптер
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_lesson32, names);

        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson32_ArrowBackWrapLL:
                startActivity(new Intent(Lesson32_SimpleList.this, Main_Activity.class));
                break;
        }
    }
}