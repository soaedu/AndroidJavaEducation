package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sostrovsky.androidjavaedu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson37_SimpleAdapter extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    // имена атрибутов для Map
    private final String ATTRIBUTE_NAME_TEXT    = "text";
    private final String ATTRIBUTE_NAME_CHECKED = "checked";
    private final String ATTRIBUTE_NAME_IMAGE   = "image";

    private ListView mSimpleLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson37);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson37_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);


        // массивы данных
        String[] texts = { "sometext 1", "sometext 2", "sometext 3", "sometext 4", "sometext 5" };
        boolean[] checked = { true, false, false, true, false };
        int img = R.mipmap.ic_launcher;

        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(texts.length);

        Map<String, Object> m;

        for (int i = 0; i < texts.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, texts[i]);
            m.put(ATTRIBUTE_NAME_CHECKED, checked[i]);
            m.put(ATTRIBUTE_NAME_IMAGE, img);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_CHECKED, ATTRIBUTE_NAME_IMAGE };

        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.Lesson37_Text_TV, R.id.Lesson37_Checked_CHB, R.id.Lesson37_ListView_IV};

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.list_item_lesson37, from, to);

        // определяем список и присваиваем ему адаптер
        mSimpleLV = (ListView) findViewById(R.id.Lesson37_Simple_LV);
        mSimpleLV.setAdapter(sAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson37_ArrowBackWrapLL:
                startActivity(new Intent(Lesson37_SimpleAdapter.this, Main_Activity.class));
                break;
        }
    }
}