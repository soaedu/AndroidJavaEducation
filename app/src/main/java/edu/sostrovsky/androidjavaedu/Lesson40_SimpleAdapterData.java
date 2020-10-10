package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
public class Lesson40_SimpleAdapterData extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private static final int CM_DELETE_ID = 1;

    // имена атрибутов для Map
    private final String ATTRIBUTE_NAME_TEXT = "text";
    private final String ATTRIBUTE_NAME_IMAGE = "image";

    private ListView mSimpleLV;

    private SimpleAdapter mAdapter;

    private ArrayList<Map<String, Object>> mData;

    private Map<String, Object> m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson40);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson40_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        // упаковываем данные в понятную для адаптера структуру
        mData = new ArrayList<Map<String, Object>>();

        for (int i = 1; i < 5; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "sometext " + i);
            m.put(ATTRIBUTE_NAME_IMAGE, R.mipmap.ic_launcher);
            mData.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE };

        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.Lesson40_TV, R.id.Lesson40_IV };

        // создаем адаптер
        mAdapter = new SimpleAdapter(this, mData, R.layout.list_item_lesson40, from, to);

        // определяем список и присваиваем ему адаптер
        mSimpleLV = (ListView) findViewById(R.id.Lesson40_Simple_LV);
        mSimpleLV.setAdapter(mAdapter);

        registerForContextMenu(mSimpleLV);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson40_ArrowBackWrapLL:
                startActivity(new Intent(Lesson40_SimpleAdapterData.this, Main_Activity.class));
                break;
        }
    }

    public void onButtonClick(View v) {
        // создаем новый Map
        m = new HashMap<String, Object>();
        m.put(ATTRIBUTE_NAME_TEXT, "sometext " + (mData.size() + 1));
        m.put(ATTRIBUTE_NAME_IMAGE, R.mipmap.ic_launcher);

        // добавляем его в коллекцию
        mData.add(m);

        // уведомляем, что данные изменились
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, CM_DELETE_ID, 0, getResources().getString(R.string.lesson40_delete_row_text));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == CM_DELETE_ID) {

            // получаем инфу о пункте списка
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            // удаляем Map из коллекции, используя позицию пункта в списке
            mData.remove(acmi.position);

            // уведомляем, что данные изменились
            mAdapter.notifyDataSetChanged();

            return true;
        }

        return super.onContextItemSelected(item);
    }
}