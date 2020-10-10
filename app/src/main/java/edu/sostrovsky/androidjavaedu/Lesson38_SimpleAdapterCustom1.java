package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson38_SimpleAdapterCustom1  extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    // имена атрибутов для Map
    private final String ATTRIBUTE_NAME_TEXT = "text";
    private final String ATTRIBUTE_NAME_VALUE = "value";
    private final String ATTRIBUTE_NAME_IMAGE = "image";

    // картинки для отображения динамики
    private final int positive = android.R.drawable.arrow_up_float;
    private final int negative = android.R.drawable.arrow_down_float;

    private ListView lvSimple;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson38);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson38_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        // массив данных
        int[] values = { 8, 4, -3, 2, -5, 0, 3, -6, 1, -1 };

        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(values.length);

        Map<String, Object> m;

        int img = 0;

        for (int i = 0; i < values.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1));
            m.put(ATTRIBUTE_NAME_VALUE, values[i]);

            if (values[i] == 0)
                img = 0;
            else
                img = (values[i] > 0) ? positive : negative;

            m.put(ATTRIBUTE_NAME_IMAGE, img);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_VALUE, ATTRIBUTE_NAME_IMAGE };

        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.Lesson38_Text_TV, R.id.Lesson38_ListView_TV, R.id.Lesson38_ListView_IV };

        // создаем адаптер
        MySimpleAdapter sAdapter = new MySimpleAdapter(this, data, R.layout.list_item_lesson38, from, to);

        // определяем список и присваиваем ему адаптер
        lvSimple = (ListView) findViewById(R.id.Lesson38_Simple_LV);
        lvSimple.setAdapter(sAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson38_ArrowBackWrapLL:
                startActivity(new Intent(Lesson38_SimpleAdapterCustom1.this, Main_Activity.class));
                break;
        }
    }

    class MySimpleAdapter extends SimpleAdapter {

        public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public void setViewText(TextView v, String text) {
            // метод супер-класса, который вставляет текст
            super.setViewText(v, text);

            // если нужный нам TextView, то разрисовываем
            if (v.getId() == R.id.Lesson38_ListView_TV) {
                int i = Integer.parseInt(text);
                if (i < 0) v.setTextColor(Color.RED); else
                if (i > 0) v.setTextColor(Color.GREEN);
            }
        }

        @Override
        public void setViewImage(ImageView v, int value) {
            // метод супер-класса
            super.setViewImage(v, value);

            // разрисовываем ImageView
            if (value == negative) v.setBackgroundColor(Color.RED); else
            if (value == positive) v.setBackgroundColor(Color.GREEN);
        }
    }
}