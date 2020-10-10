package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.sostrovsky.androidjavaedu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson39_SimpleAdapterCustom2  extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    // имена атрибутов для Map
    private final String ATTRIBUTE_NAME_TEXT = "text";
    private final String ATTRIBUTE_NAME_PB = "pb";
    private final String ATTRIBUTE_NAME_LL = "ll";

    private ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson39);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson39_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        // массив данных
        int load[] = { 41, 48, 22, 35, 30, 67, 51, 88 };

        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(load.length);

        Map<String, Object> m;

        for (int i = 0; i < load.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i+1) + ". Load: " + load[i] + "%");
            m.put(ATTRIBUTE_NAME_PB, load[i]);
            m.put(ATTRIBUTE_NAME_LL, load[i]);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB, ATTRIBUTE_NAME_LL };

        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.Lesson39_Load_TV, R.id.Lesson39_Load_PB, R.id.Lesson39_Load_LL};

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.list_item_lesson39, from, to);

        // Указываем адаптеру свой биндер
        sAdapter.setViewBinder(new MyViewBinder());

        // определяем список и присваиваем ему адаптер
        lvSimple = (ListView) findViewById(R.id.Lesson39_Simple_LV);
        lvSimple.setAdapter(sAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson39_ArrowBackWrapLL:
                startActivity(new Intent(Lesson39_SimpleAdapterCustom2.this, Main_Activity.class));
                break;
        }
    }

    class MyViewBinder implements SimpleAdapter.ViewBinder {

        int red = getResources().getColor(R.color.lesson39_Red);
        int orange = getResources().getColor(R.color.lesson39_orange);
        int green = getResources().getColor(R.color.lesson39_green);

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {

            int i = 0;

            switch (view.getId()) {

                // LinearLayout
                case R.id.Lesson39_Load_LL:
                    i = ((Integer) data).intValue();

                    if (i < 40)
                        view.setBackgroundColor(green);
                    else if (i < 70)
                        view.setBackgroundColor(orange);
                    else
                        view.setBackgroundColor(red);

                    return true;

                // ProgressBar
                case R.id.Lesson39_Load_PB:
                    i = ((Integer) data).intValue();

                    ((ProgressBar)view).setProgress(i);

                    return true;
            }
            return false;
        }
    }
}