package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 15.12.2016.
 */
public class Lesson53_AlertDialogItemsMulti extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mItemsBtn;
    private Button mAdapterBtn;
    private Button mCursorBtn;

    private final int DIALOG_ITEMS = 1;
    private final int DIALOG_CURSOR = 3;

    private Lesson53_DB db;

    private Cursor cursor;

    private String data[] = { "one", "two", "three", "four" };

    private boolean chkd[] = { false, true, true, false };

    private final static String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson53);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson53_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        // открываем подключение к БД
        db = new Lesson53_DB(this);
        db.open();

        cursor = db.getAllData();
        startManagingCursor(cursor);

        mItemsBtn = (Button) findViewById(R.id.Lesson53_Items_Btn);
        mItemsBtn.setOnClickListener(this);

        mCursorBtn = (Button) findViewById(R.id.Lesson53_Cursor_Btn);
        mCursorBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson53_ArrowBackWrapLL:
                startActivity(new Intent(Lesson53_AlertDialogItemsMulti.this, Main_Activity.class));
                break;
            case R.id.Lesson53_Items_Btn:
                showDialog(DIALOG_ITEMS);
                break;
            case R.id.Lesson53_Cursor_Btn:
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
                adb.setMultiChoiceItems(data, chkd, myItemsMultiClickListener);
                break;
            // курсор
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor_text);
                adb.setMultiChoiceItems(cursor, Lesson53_DB.COLUMN_CHK, Lesson53_DB.COLUMN_TXT, myCursorMultiClickListener);
                break;
        }
        adb.setPositiveButton(R.string.ok_text, myBtnClickListener);
        return adb.create();
    }

    // обработчик для списка массива
    DialogInterface.OnMultiChoiceClickListener myItemsMultiClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            Log.d(LOG_TAG, "which = " + which + ", isChecked = " + isChecked);
        }
    };

    // обработчик для списка курсора
    DialogInterface.OnMultiChoiceClickListener myCursorMultiClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            ListView lv = ((AlertDialog) dialog).getListView();
            Log.d(LOG_TAG, "which = " + which + ", isChecked = " + isChecked);
            db.changeRec(which, isChecked);
            cursor.requery();
        }
    };

    // обработчик нажатия на кнопку
    DialogInterface.OnClickListener myBtnClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            SparseBooleanArray sbArray = ((AlertDialog)dialog).getListView().getCheckedItemPositions();
            for (int i = 0; i < sbArray.size(); i++) {
                int key = sbArray.keyAt(i);
                if (sbArray.get(key))
                    Log.d("qwe", "checked: " + key);
            }
        }
    };

    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}