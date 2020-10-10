package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson34_SimpleListEvents extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    private ListView lvMain;
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson34);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson34_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        lvMain = (ListView) findViewById(R.id.Lesson34_Main_LV);

        // Создаем адаптер, используя массив из файла ресурсов
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_1);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG_TAG, "itemClick: position = " + position + ", id = " + id);
            }
        });

        lvMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG_TAG, "itemSelect: position = " + position + ", id = " + id);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(LOG_TAG, "itemSelect: nothing");
            }
        });

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d(LOG_TAG, "scrollState = " + scrollState);
            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(LOG_TAG, "scroll: firstVisibleItem = " + firstVisibleItem + ", visibleItemCount" + visibleItemCount + ", totalItemCount" + totalItemCount);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson34_ArrowBackWrapLL:
                startActivity(new Intent(Lesson34_SimpleListEvents.this, Main_Activity.class));
                break;
        }
    }
}