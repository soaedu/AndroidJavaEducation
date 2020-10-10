package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson33_SimpleListChoice  extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    private ListView lvMain;
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson33);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson33_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        lvMain = (ListView) findViewById(R.id.Lesson33_Main_LV);

        // устанавливаем режим выбора пунктов списка
        // lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Создаем адаптер, используя массив из файла ресурсов
        // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_single_choice);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_multiple_choice);
        lvMain.setAdapter(adapter);

        Button btnChecked = (Button) findViewById(R.id.Lesson33_Checked_BTN);
        btnChecked.setOnClickListener(this);

        // получаем массив из файла ресурсов
        names = getResources().getStringArray(R.array.names);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson33_ArrowBackWrapLL:
                startActivity(new Intent(Lesson33_SimpleListChoice.this, Main_Activity.class));
                break;
            case R.id.Lesson33_Checked_BTN:
                // пишем в лог выделенный элемент
                // Log.d(LOG_TAG, "checked: " + names[lvMain.getCheckedItemPosition()]);

                // пишем в лог выделенные элементы
                Log.d(LOG_TAG, "checked: ");
                SparseBooleanArray sbArray = lvMain.getCheckedItemPositions();
                for (int i = 0; i < sbArray.size(); i++) {
                    int key = sbArray.keyAt(i);
                    if (sbArray.get(key))
                        Log.d(LOG_TAG, names[key]);
                }
                break;
        }
    }
}