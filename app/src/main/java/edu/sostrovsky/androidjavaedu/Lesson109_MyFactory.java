package edu.sostrovsky.androidjavaedu;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sostrovsky.androidjavaedu.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sostrovschi on 09.01.2017
 */

public class Lesson109_MyFactory implements RemoteViewsService.RemoteViewsFactory {

    ArrayList<String> data;
    Context context;
    SimpleDateFormat sdf;

    int widgetID;

    Lesson109_MyFactory(Context ctx, Intent intent) {
        context = ctx;
        sdf = new SimpleDateFormat("HH:mm:ss");

        widgetID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        data = new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

/*    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rView = new RemoteViews(context.getPackageName(), R.layout.list_item_lesson109);
        rView.setTextViewText(R.id.Lesson109_ItemText_TV, data.get(position));
        return rView;
    }*/

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rView = new RemoteViews(context.getPackageName(), R.layout.list_item_lesson109);
        rView.setTextViewText(R.id.Lesson109_ItemText_TV, data.get(position));

        Intent clickIntent = new Intent();
        clickIntent.putExtra(Lesson109_MyWidgetProvider.ITEM_POSITION, position);
        rView.setOnClickFillInIntent(R.id.Lesson109_ItemText_TV, clickIntent);

        return rView;
    }


    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onDataSetChanged() {
        data.clear();
        data.add(sdf.format(new Date(System.currentTimeMillis())));
        data.add(String.valueOf(hashCode()));
        data.add(String.valueOf(widgetID));

        for (int i = 3; i < 15; i++) {
            data.add("Item " + i);
        }
    }

    @Override
    public void onDestroy() {

    }
}
