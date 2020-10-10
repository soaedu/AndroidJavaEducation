package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by sostrovschi on 05.01.2017
 */

public class Lesson106_WidgetConfig extends Activity {

    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;
    Intent resultValue;

    private final String LOG_TAG = "myLogs";

    public final static String WIDGET_PREF = "widget_pref";
    public final static String WIDGET_TEXT = "widget_text_";
    public final static String WIDGET_COLOR = "widget_color_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate config");

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

        setContentView(R.layout.activity_lesson106);
    }


    public void onClick(View v) {
        int selRBColor = ((RadioGroup) findViewById(R.id.Lesson106_Color_RG)).getCheckedRadioButtonId();
        int color = Color.RED;

        switch (selRBColor) {
            case R.id.Lesson106_Red_RB:
                color = Color.parseColor("#66ff0000");
                break;
            case R.id.Lesson106_Green_RB:
                color = Color.parseColor("#6600ff00");
                break;
            case R.id.Lesson106_Blue_RB:
                color = Color.parseColor("#660000ff");
                break;
        }

        EditText etText = (EditText) findViewById(R.id.Lesson106_ET);

        // Записываем значения с экрана в Preferences
        SharedPreferences sp = getSharedPreferences(WIDGET_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(WIDGET_TEXT + widgetID, etText.getText().toString());
        editor.putInt(WIDGET_COLOR + widgetID, color);
        editor.commit();

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        Lesson106_SimpleWidget.updateWidget(this, appWidgetManager, sp, widgetID);

        // положительный ответ
        setResult(RESULT_OK, resultValue);

        Log.d(LOG_TAG, "finish config " + widgetID);
        finish();
    }
}
