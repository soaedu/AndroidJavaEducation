package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by sostrovschi on 05.01.2017
 */

public class Lesson108_WidgetConfig extends     Activity
                                    implements  View.OnClickListener {

    private final String LOG_TAG = "myLogs";

    public final static String WIDGET_PREF = "widget_pref";
    public final static String WIDGET_TIME_FORMAT = "widget_time_format_";
    public final static String WIDGET_COUNT = "widget_count_";

    private int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;

    private Intent resultValue;
    private SharedPreferences sp;
    private EditText etFormat;

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson108);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson108_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        //////////////////////////////////////////////////////////////////////////////////

        // извлекаем ID конфигурируемого виджета
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // и проверяем его корректность
        if (widgetID == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        // формируем intent ответа
        resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);

        // отрицательный ответ
        setResult(RESULT_CANCELED, resultValue);

//        setContentView(R.layout.config);

        sp = getSharedPreferences(WIDGET_PREF, MODE_PRIVATE);
        etFormat = (EditText) findViewById(R.id.Lesson108_Format_ET);
        etFormat.setText(sp.getString(WIDGET_TIME_FORMAT + widgetID, "HH:mm:ss"));

        int cnt = sp.getInt(Lesson108_WidgetConfig.WIDGET_COUNT + widgetID, -1);
        if (cnt == -1) sp.edit().putInt(WIDGET_COUNT + widgetID, 0);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson108_ArrowBackWrapLL:
                startActivity(new Intent(Lesson108_WidgetConfig.this, Main_Activity.class));
                break;
            case R.id.Lesson108_Ok_Btn:
                sp.edit().putString(WIDGET_TIME_FORMAT + widgetID, etFormat.getText().toString()).commit();
                // Lesson108_MyWidget.updateWidget(this, AppWidgetManager.getInstance(this), widgetID);
                Lesson108_MyWidget.updateWidget(this, AppWidgetManager.getInstance(this), widgetID, false);
                setResult(RESULT_OK, resultValue);

                finish();
                break;
            default:
                break;
        }
    }
}
