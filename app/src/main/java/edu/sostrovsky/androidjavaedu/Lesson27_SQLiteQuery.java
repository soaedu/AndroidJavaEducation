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
import android.widget.RadioGroup;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson27_SQLiteQuery   extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    private Button mAllBTN, mFunctionBTN, mPeopleBTN, mSortBTN, mGroupBTN, mHavingBTN;

    private EditText mFunctionET, mPeopleET, mRegionPeopleET;

    private RadioGroup mSortingRG;

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    private String name[] = { "Китай", "США", "Бразилия", "Россия", "Япония", "Германия", "Египет", "Италия", "Франция", "Канада" };
    private int people[] = { 1400, 311, 195, 142, 128, 82, 80, 60, 66, 35 };
    private String region[] = { "Азия", "Америка", "Америка", "Европа", "Азия", "Европа", "Африка", "Европа", "Европа", "Америка" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson27);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson27_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mAllBTN = (Button) findViewById(R.id.Lesson27_All_BTN);
        mAllBTN.setOnClickListener(this);

        mFunctionBTN = (Button) findViewById(R.id.Lesson27_Function_BTN);
        mFunctionBTN.setOnClickListener(this);

        mPeopleBTN = (Button) findViewById(R.id.Lesson27_Population_BTN);
        mPeopleBTN.setOnClickListener(this);

        mSortBTN = (Button) findViewById(R.id.Lesson27_Sort_BTN);
        mSortBTN.setOnClickListener(this);

        mGroupBTN = (Button) findViewById(R.id.Lesson27_Group_BTN);
        mGroupBTN.setOnClickListener(this);

        mHavingBTN = (Button) findViewById(R.id.Lesson27_Having_BTN);
        mHavingBTN.setOnClickListener(this);

        mFunctionET = (EditText) findViewById(R.id.Lesson27_Function_ET);
        mPeopleET = (EditText) findViewById(R.id.Lesson27_Population_ET);
        mRegionPeopleET = (EditText) findViewById(R.id.Lesson27_RegionPeople_ET);

        mSortingRG = (RadioGroup) findViewById(R.id.Lesson27_Sorting_RG);

        dbHelper = new DBHelper(this);

        // подключаемся к базе
        db = dbHelper.getWritableDatabase();

        // проверка существования записей
        Cursor c = db.query("myCountriesTable", null, null, null, null, null, null);

        if (c.getCount() == 0) {

            ContentValues cv = new ContentValues();

            // заполним таблицу
            for (int i = 0; i < 10; i++) {
                cv.put("name", name[i]);
                cv.put("people", people[i]);
                cv.put("region", region[i]);

                Log.d(LOG_TAG, "id = " + db.insert("myCountriesTable", null, cv));
            }
        }
        c.close();

        dbHelper.close();

        // эмулируем нажатие кнопки btnAll
        onClick(mAllBTN);
    }

    @Override
    public void onClick(View view) {

        // подключаемся к базе
        db = dbHelper.getWritableDatabase();

        // данные с экрана
        String sFunc = mFunctionET.getText().toString();
        String sPeople = mPeopleET.getText().toString();
        String sRegionPeople = mRegionPeopleET.getText().toString();

        // переменные для query
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        // курсор
        Cursor c = null;

        switch (view.getId()) {

            case R.id.Lesson27_ArrowBackWrapLL:
                                                startActivity(new Intent(Lesson27_SQLiteQuery.this, Main_Activity.class));
                                                break;

            // Все записи
            case R.id.Lesson27_All_BTN:         Log.d(LOG_TAG, "--- Все записи ---");

                                                c = db.query("myCountriesTable", null, null, null, null, null, null);
                                                break;

            // Функция
            case R.id.Lesson27_Function_BTN:    Log.d(LOG_TAG, "--- Функция " + sFunc + " ---");

                                                columns = new String[] { sFunc };
                                                c = db.query("myCountriesTable", columns, null, null, null, null, null);
                                                break;
            // Население больше, чем
            case R.id.Lesson27_Population_BTN:  Log.d(LOG_TAG, "--- Население больше " + sPeople + " ---");

                                                selection = "people > ?";
                                                selectionArgs = new String[] { sPeople };
                                                c = db.query("myCountriesTable", null, selection, selectionArgs, null, null, null);
                                                break;
            // Население по региону
            case R.id.Lesson27_Group_BTN:       Log.d(LOG_TAG, "--- Население по региону ---");

                                                columns = new String[] { "region", "sum(people) as people" };
                                                groupBy = "region";
                                                c = db.query("myCountriesTable", columns, null, null, groupBy, null, null);
                                                break;
            // Население по региону больше чем
            case R.id.Lesson27_Having_BTN:      Log.d(LOG_TAG, "--- Регионы с населением больше " + sRegionPeople + " ---");

                                                columns = new String[] { "region", "sum(people) as people" };
                                                groupBy = "region";
                                                having = "sum(people) > " + sRegionPeople;
                                                c = db.query("myCountriesTable", columns, null, null, groupBy, having, null);
                                                break;
            // Сортировка
            case R.id.Lesson27_Sort_BTN:
                                                // сортировка по
                                                switch (mSortingRG.getCheckedRadioButtonId()) {
                                                    // наименование
                                                    case R.id.Lesson27_CountryName_RB:
                                                                                        Log.d(LOG_TAG, "--- Сортировка по наименованию ---");

                                                                                        orderBy = "name";
                                                                                        break;
                                                    // население
                                                    case R.id.Lesson27_Population_RB:
                                                                                        Log.d(LOG_TAG, "--- Сортировка по населению ---");

                                                                                        orderBy = "people";
                                                                                        break;
                                                    // регион
                                                    case R.id.Lesson27_Region_RB:
                                                                                        Log.d(LOG_TAG, "--- Сортировка по региону ---");

                                                                                        orderBy = "region";
                                                                                        break;
                                                }

                                                c = db.query("myCountriesTable", null, null, null, null, null, orderBy);
                                                break;
        }

        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);

                } while (c.moveToNext());
            }
            c.close();
        } else
            Log.d(LOG_TAG, "Cursor is null");

        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myCountryDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");

            // создаем таблицу с полями
            db.execSQL("create table myCountriesTable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "people integer,"
                    + "region text"
                    + ");");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}