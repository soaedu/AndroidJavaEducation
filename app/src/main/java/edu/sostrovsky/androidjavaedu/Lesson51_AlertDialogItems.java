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

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 15.12.2016.
 */
public class Lesson51_AlertDialogItems  extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mItemsBtn;
    private Button mAdapterBtn;
    private Button mCursorBtn;

    private final int DIALOG_ITEMS = 1;
    private final int DIALOG_ADAPTER = 2;
    private final int DIALOG_CURSOR = 3;

    private int cnt = 0;

    private Lesson51_DB db;

    private Cursor cursor;

    private String data[] = { "one", "two", "three", "four" };

    private final static String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson51);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson51_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        // открываем подключение к БД
        db = new Lesson51_DB(this);
        db.open();

        cursor = db.getAllData();
        startManagingCursor(cursor);

        mItemsBtn = (Button) findViewById(R.id.Lesson51_Items_Btn);
        mItemsBtn.setOnClickListener(this);

        mAdapterBtn = (Button) findViewById(R.id.Lesson51_Adapter_Btn);
        mAdapterBtn.setOnClickListener(this);

        mCursorBtn = (Button) findViewById(R.id.Lesson51_Cursor_Btn);
        mCursorBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        changeCount();

        switch (view.getId()) {

            case R.id.Lesson51_ArrowBackWrapLL:
                startActivity(new Intent(Lesson51_AlertDialogItems.this, Main_Activity.class));
                break;
            case R.id.Lesson51_Items_Btn:
                showDialog(DIALOG_ITEMS);
                break;
            case R.id.Lesson51_Adapter_Btn:
                showDialog(DIALOG_ADAPTER);
                break;
            case R.id.Lesson51_Cursor_Btn:
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
                adb.setItems(data, myClickListener);
                break;
            // адаптер
            case DIALOG_ADAPTER:
                adb.setTitle(R.string.adapter_text);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, data);
                adb.setAdapter(adapter, myClickListener);
                break;
            // курсор
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor_text);
                adb.setCursor(cursor, myClickListener, Lesson51_DB.COLUMN_TXT);
                break;
        }
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

    // обработчик нажатия на пункт списка диалога
    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            // выводим в лог позицию нажатого элемента
            Log.d(LOG_TAG, "clicked on item = " + which);
        }
    };

    // меняем значение счетчика
    void changeCount() {
        cnt++;
        // обновляем массив
        data[3] = String.valueOf(cnt);
        // обновляем БД
        db.changeRec(4, String.valueOf(cnt));
        cursor.requery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}