package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson41_SimpleCursorAdapter   extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private static final int CM_DELETE_ID = 1;

    private ListView mSimpleLV;

    private Lesson41_DB lesson41Db;

    private SimpleCursorAdapter scAdapter;

    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson41);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson41_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        // открываем подключение к БД
        lesson41Db = new Lesson41_DB(this);
        lesson41Db.open();

        // получаем курсор
        cursor = lesson41Db.getAllData();
        startManagingCursor(cursor);

        // формируем столбцы сопоставления
        String[] from = new String[] { Lesson41_DB.COLUMN_IMG, Lesson41_DB.COLUMN_TXT };
        int[] to = new int[] { R.id.Lesson41_IV, R.id.Lesson41_TV };

        // создааем адаптер и настраиваем список
        scAdapter = new SimpleCursorAdapter(this, R.layout.list_item_lesson41, cursor, from, to);

        mSimpleLV = (ListView) findViewById(R.id.Lesson41_Simple_LV);
        mSimpleLV.setAdapter(scAdapter);

        // добавляем контекстное меню к списку
        registerForContextMenu(mSimpleLV);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson41_ArrowBackWrapLL:
                startActivity(new Intent(Lesson41_SimpleCursorAdapter.this, Main_Activity.class));
                break;
        }
    }

    public void onButtonClick(View v) {
        // добавляем запись
        lesson41Db.addRec("sometext " + (cursor.getCount() + 1), R.mipmap.ic_launcher);

        // обновляем курсор
        cursor.requery();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, getResources().getString(R.string.lesson40_delete_row_text));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == CM_DELETE_ID) {
            // получаем из пункта контекстного меню данные по пункту списка
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            // извлекаем id записи и удаляем соответствующую запись в БД
            lesson41Db.delRec(acmi.id);

            // обновляем курсор
            cursor.requery();

            return true;
        }

        return super.onContextItemSelected(item);
    }

    protected void onDestroy() {
        super.onDestroy();
        // закрываем подключение при выходе
        lesson41Db.close();
    }
}