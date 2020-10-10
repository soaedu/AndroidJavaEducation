package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson26_SimpleSQLite extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    private Button mAddBTN, mReadBTN, mClearBTN, mUpdateBTN, mDeleteBTN;
    private EditText mNameET, mEmailET, mIdET;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson26);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson26_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mNameET = (EditText) findViewById(R.id.Lesson26_Name_ET);
        mEmailET = (EditText) findViewById(R.id.Lesson26_Email_ET);
        mIdET = (EditText) findViewById(R.id.Lesson26_ID_ET);

        mAddBTN = (Button) findViewById(R.id.Lesson26_Add_BTN);
        mAddBTN.setOnClickListener(this);

        mReadBTN = (Button) findViewById(R.id.Lesson26_Read_BTN);
        mReadBTN.setOnClickListener(this);

        mClearBTN = (Button) findViewById(R.id.Lesson26_Clear_BTN);
        mClearBTN.setOnClickListener(this);

        mUpdateBTN = (Button) findViewById(R.id.Lesson26_Update_BTN);
        mUpdateBTN.setOnClickListener(this);

        mDeleteBTN = (Button) findViewById(R.id.Lesson26_Delete_BTN);
        mDeleteBTN.setOnClickListener(this);

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        String name = mNameET.getText().toString();
        String email = mEmailET.getText().toString();

        String id = mIdET.getText().toString();

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (view.getId()) {

            case R.id.Lesson26_ArrowBackWrapLL:
                                                startActivity(new Intent(Lesson26_SimpleSQLite.this, Main_Activity.class));
                                                break;

            case R.id.Lesson26_Add_BTN:         Log.d(LOG_TAG, "--- Insert in mytable: ---");

                                                // подготовим данные для вставки в виде пар: наименование столбца - значение

                                                cv.put("name", name);
                                                cv.put("email", email);

                                                // вставляем запись и получаем ее ID
                                                long rowID = db.insert("mytable", null, cv);

                                                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                                                break;

            case R.id.Lesson26_Read_BTN:        Log.d(LOG_TAG, "--- Rows in mytable: ---");

                                                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                                                Cursor c = db.query("mytable", null, null, null, null, null, null);

                                                // ставим позицию курсора на первую строку выборки
                                                // если в выборке нет строк, вернется false
                                                if (c.moveToFirst()) {

                                                    // определяем номера столбцов по имени в выборке
                                                    int idColIndex = c.getColumnIndex("id");
                                                    int nameColIndex = c.getColumnIndex("name");
                                                    int emailColIndex = c.getColumnIndex("email");

                                                    do {
                                                        // получаем значения по номерам столбцов и пишем все в лог
                                                        Log.d(LOG_TAG,
                                                                "ID = " + c.getInt(idColIndex) +
                                                                ", name = " + c.getString(nameColIndex) +
                                                                ", email = " + c.getString(emailColIndex));

                                                        // переход на следующую строку
                                                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
                                                    } while (c.moveToNext());
                                                } else
                                                    Log.d(LOG_TAG, "0 rows");
                                                c.close();
                                                break;

            case R.id.Lesson26_Clear_BTN:       Log.d(LOG_TAG, "--- Clear mytable: ---");

                                                // удаляем все записи
                                                int clearCount = db.delete("mytable", null, null);

                                                Log.d(LOG_TAG, "deleted rows count = " + clearCount);
                                                break;

            case R.id.Lesson26_Update_BTN:     if (id.equalsIgnoreCase("")) {
                                                    break;
                                                }

                                                Log.d(LOG_TAG, "--- Update mytable: ---");

                                                // подготовим значения для обновления
                                                cv.put("name", name);
                                                cv.put("email", email);

                                                // обновляем по id
                                                int updCount = db.update("mytable", cv, "id = ?", new String[] { id });

                                                Log.d(LOG_TAG, "updated rows count = " + updCount);
                                                break;

            case R.id.Lesson26_Delete_BTN:      if (id.equalsIgnoreCase("")) {
                                                    break;
                                                }

                                                Log.d(LOG_TAG, "--- Delete from mytable: ---");

                                                // удаляем по id
                                                int delCount = db.delete("mytable", "id = " + id, null);

                                                Log.d(LOG_TAG, "deleted rows count = " + delCount);
                                                break;
        }

        // закрываем подключение к БД
        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");

            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "email text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}