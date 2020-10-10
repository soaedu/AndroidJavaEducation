package edu.sostrovsky.androidjavaedu;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.sostrovsky.androidjavaedu.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by OS1 on 08.01.2017.
 */
public class Lesson108_MyWidget extends AppWidgetProvider {

    final static String ACTION_CHANGE = "android.appwidget.AppWidgetProvider.change_count";

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        // обновляем все экземпляры
        for (int i : appWidgetIds) {
            // updateWidget(context, appWidgetManager, i);
            updateWidget(context, appWidgetManager, i, false);
        }
    }

    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);

        // Удаляем Preferences
        SharedPreferences.Editor editor = context.getSharedPreferences(Lesson108_WidgetConfig.WIDGET_PREF, Context.MODE_PRIVATE).edit();

        for (int widgetID : appWidgetIds) {
            editor.remove(Lesson108_WidgetConfig.WIDGET_TIME_FORMAT + widgetID);
            editor.remove(Lesson108_WidgetConfig.WIDGET_COUNT + widgetID);
        }

        editor.commit();
    }

    // static void updateWidget(Context ctx, AppWidgetManager appWidgetManager, int widgetID) {
    static void updateWidget(Context ctx, AppWidgetManager appWidgetManager, int widgetID, boolean changeOnlyCounter) {
        SharedPreferences sp = ctx.getSharedPreferences(Lesson108_WidgetConfig.WIDGET_PREF, Context.MODE_PRIVATE);

        // Читаем формат времени и определяем текущее время
        String timeFormat = sp.getString(Lesson108_WidgetConfig.WIDGET_TIME_FORMAT + widgetID, null);
        if (timeFormat == null) return;

        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        String currentTime = sdf.format(new Date(System.currentTimeMillis()));

        // Читаем счетчик
        String count = String.valueOf(sp.getInt(Lesson108_WidgetConfig.WIDGET_COUNT + widgetID, 0));

        // Помещаем данные в текстовые поля
        RemoteViews widgetView = new RemoteViews(ctx.getPackageName(), R.layout.widget_lesson108);
        // widgetView.setTextViewText(R.id.Lesson108_Time_TV, currentTime);

        // если обновлять надо не только счетчик
        if(!changeOnlyCounter)
            widgetView.setTextViewText(R.id.Lesson108_Time_TV, currentTime);

        widgetView.setTextViewText(R.id.Lesson108_Count_TV, count);

        // Конфигурационный экран (первая зона)
        Intent configIntent = new Intent(ctx, Lesson108_WidgetConfig.class);
        configIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
        configIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent = PendingIntent.getActivity(ctx, widgetID, configIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.Lesson108_PressConfig_TV, pIntent);

        // Обновление виджета (вторая зона)
        Intent updateIntent = new Intent(ctx, Lesson108_MyWidget.class);
        updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                new int[] { widgetID });
        pIntent = PendingIntent.getBroadcast(ctx, widgetID, updateIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.Lesson108_PressUpdate_TV, pIntent);

        // Счетчик нажатий (третья зона)
        Intent countIntent = new Intent(ctx, Lesson108_MyWidget.class);
        countIntent.setAction(ACTION_CHANGE);
        countIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        pIntent = PendingIntent.getBroadcast(ctx, widgetID, countIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.Lesson108_PressCount_TV, pIntent);

        Intent showWebIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        pIntent = PendingIntent.getActivity(ctx, widgetID, showWebIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.Lesson108_ShowWebPage_TV, pIntent);

        // Обновляем виджет
        appWidgetManager.updateAppWidget(widgetID, widgetView);
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        // Проверяем, что это intent от нажатия на третью зону
        if (intent.getAction().equalsIgnoreCase(ACTION_CHANGE)) {

            // извлекаем ID экземпляра
            int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
            Bundle extras = intent.getExtras();

            if (extras != null) {
                mAppWidgetId = extras.getInt(
                        AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);

            }

            if (mAppWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                // Читаем значение счетчика, увеличиваем на 1 и записываем
                SharedPreferences sp = context.getSharedPreferences(Lesson108_WidgetConfig.WIDGET_PREF, Context.MODE_PRIVATE);

                int cnt = sp.getInt(Lesson108_WidgetConfig.WIDGET_COUNT + mAppWidgetId,  0);

                sp.edit().putInt(Lesson108_WidgetConfig.WIDGET_COUNT + mAppWidgetId, ++cnt).commit();

                // Обновляем виджет
                // updateWidget(context, AppWidgetManager.getInstance(context), mAppWidgetId);
                updateWidget(context, AppWidgetManager.getInstance(context), mAppWidgetId, true);
            }
        }
    }
}
