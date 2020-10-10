package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 15.12.2016.
 */
public class Lesson52_AlertDialogItemsSingle    extends     Activity
                                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mItemsBtn;
    private Button mAdapterBtn;
    private Button mCursorBtn;

    private final int DIALOG_ITEMS = 1;
    private final int DIALOG_ADAPTER = 2;
    private final int DIALOG_CURSOR = 3;

    private Lesson52_DB db;

    private Cursor cursor;

    private String data[] = { "one", "two", "three", "four" };

    private final static String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson52);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson52_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        // открываем подключение к БД
        db = new Lesson52_DB(this);
        db.open();

        cursor = db.getAllData();
        startManagingCursor(cursor);

        mItemsBtn = (Button) findViewById(R.id.Lesson52_Items_Btn);
        mItemsBtn.setOnClickListener(this);

        mAdapterBtn = (Button) findViewById(R.id.Lesson52_Adapter_Btn);
        mAdapterBtn.setOnClickListener(this);

        mCursorBtn = (Button) findViewById(R.id.Lesson52_Cursor_Btn);
        mCursorBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson52_ArrowBackWrapLL:
                startActivity(new Intent(Lesson52_AlertDialogItemsSingle.this, Main_Activity.class));
                break;
            case R.id.Lesson52_Items_Btn:
                showDialog(DIALOG_ITEMS);
                break;
            case R.id.Lesson52_Adapter_Btn:
                showDialog(DIALOG_ADAPTER);
                break;
            case R.id.Lesson52_Cursor_Btn:
                showDialog(DIALOG_CURSOR);
                break;
            default:
                break;
        }
    }

    protected Dialog onCreateDialog(int id) {

        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        switch (id) {
            // массив
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items_text);
                adb.setSingleChoiceItems(data, -1, myClickListener);
                break;
            // адаптер
            case DIALOG_ADAPTER:
                adb.setTitle(R.string.adapter_text);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, data);
                adb.setSingleChoiceItems(adapter, -1, myClickListener);
                break;
            // курсор
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor_text);
                adb.setSingleChoiceItems(cursor, -1, Lesson52_DB.COLUMN_TXT, myClickListener);
                break;
        }
        adb.setPositiveButton(R.string.ok_text, myClickListener);
        return adb.create();
    }

    protected void onPrepareDialog(int id, Dialog dialog) {
        // получаем доступ к адаптеру списка диалога
        AlertDialog aDialog = (AlertDialog) dialog;

        ListAdapter lAdapter = aDialog.getListView().getAdapter();

        switch (id) {
            case DIALOG_ITEMS:
            case DIALOG_ADAPTER:
                // проверка возможности преобразования
                if (lAdapter instanceof BaseAdapter) {
                    // преобразование и вызов метода-уведомления о новых данных
                    BaseAdapter bAdapter = (BaseAdapter) lAdapter;
                    bAdapter.notifyDataSetChanged();
                }
                break;
            case DIALOG_CURSOR:
                break;
            default:
                break;
        }
    };

    // обработчик нажатия на пункт списка диалога или кнопку
    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {

            ListView lv = ((AlertDialog) dialog).getListView();

            if (which == Dialog.BUTTON_POSITIVE)
                // выводим в лог позицию выбранного элемента
                Log.d(LOG_TAG, "pos = " + lv.getCheckedItemPosition());
            else
                // выводим в лог позицию нажатого элемента
                Log.d(LOG_TAG, "which = " + which);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}