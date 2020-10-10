package edu.sostrovsky.androidjavaedu;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import com.sostrovsky.androidjavaedu.R;

import java.util.Arrays;

/**
 * Created by sostrovschi on 05.01.2017
 */

public class Lesson106_SimpleWidget extends AppWidgetProvider {

    final static String LOG_TAG = "myLogs";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(LOG_TAG, "onEnabled");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(LOG_TAG, "onUpdate " + Arrays.toString(appWidgetIds));

        SharedPreferences sp = context.getSharedPreferences(Lesson106_WidgetConfig.WIDGET_PREF, Context.MODE_PRIVATE);

        for (int id : appWidgetIds) {
            updateWidget(context, appWidgetManager, sp, id);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(LOG_TAG, "onDeleted " + Arrays.toString(appWidgetIds));

        // Удаляем Preferences
        SharedPreferences.Editor editor = context.getSharedPreferences(Lesson106_WidgetConfig.WIDGET_PREF, Context.MODE_PRIVATE).edit();

        for (int widgetID : appWidgetIds) {
            editor.remove(Lesson106_WidgetConfig.WIDGET_TEXT + widgetID);
            editor.remove(Lesson106_WidgetConfig.WIDGET_COLOR + widgetID);
        }
        editor.commit();
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "onDisabled");
    }

    static void updateWidget(Context context, AppWidgetManager appWidgetManager, SharedPreferences sp, int widgetID) {
        Log.d(LOG_TAG, "updateWidget " + widgetID);

        // Читаем параметры Preferences
        String widgetText = sp.getString(Lesson106_WidgetConfig.WIDGET_TEXT + widgetID, null);
        if (widgetText == null) return;
        int widgetColor = sp.getInt(Lesson106_WidgetConfig.WIDGET_COLOR + widgetID, 0);

        // Настраиваем внешний вид виджета
        RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget_lesson106);
        widgetView.setTextViewText(R.id.Lesson106_TV, widgetText);
        widgetView.setInt(R.id.Lesson106_TV, "setBackgroundColor", widgetColor);

        // Обновляем виджет
        appWidgetManager.updateAppWidget(widgetID, widgetView);
    }
}
