package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorTreeAdapter;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson42_SimpleCursorTreeAdapter   extends     Activity
                                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private ExpandableListView elvMain;
    private Lesson42_DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson42);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson42_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        // подключаемся к БД
        db = new Lesson42_DB(this);
        db.open();

        // готовим данные по группам для адаптера
        Cursor cursor = db.getCompanyData();
        startManagingCursor(cursor);

        // сопоставление данных и View для групп
        String[] groupFrom = { Lesson42_DB.COMPANY_COLUMN_NAME };
        int[] groupTo = { android.R.id.text1 };

        // сопоставление данных и View для элементов
        String[] childFrom = { Lesson42_DB.PHONE_COLUMN_NAME };
        int[] childTo = { android.R.id.text1 };

        // создаем адаптер и настраиваем список
        SimpleCursorTreeAdapter sctAdapter = new MyAdapter(this, cursor, android.R.layout.simple_expandable_list_item_1, groupFrom,
                                                            groupTo, android.R.layout.simple_list_item_1, childFrom, childTo);

        elvMain = (ExpandableListView) findViewById(R.id.Lesson42_Main_ELV);
        elvMain.setAdapter(sctAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson42_ArrowBackWrapLL:
                startActivity(new Intent(Lesson42_SimpleCursorTreeAdapter.this, Main_Activity.class));
                break;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    class MyAdapter extends SimpleCursorTreeAdapter {

        public MyAdapter(Context context, Cursor cursor, int groupLayout, String[] groupFrom, int[] groupTo, int childLayout, String[] childFrom, int[] childTo) {
            super(context, cursor, groupLayout, groupFrom, groupTo,
                    childLayout, childFrom, childTo);
        }

        protected Cursor getChildrenCursor(Cursor groupCursor) {
            // получаем курсор по элементам для конкретной группы
            int idColumn = groupCursor.getColumnIndex(Lesson42_DB.COMPANY_COLUMN_ID);

            return db.getPhoneData(groupCursor.getInt(idColumn));
        }
    }
}