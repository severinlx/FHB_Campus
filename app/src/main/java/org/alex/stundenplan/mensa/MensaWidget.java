package org.alex.stundenplan.mensa;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import org.alex.stundenplan.R;

/**
 * Created by bschramke on 07.05.15.
 */
public class MensaWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        //Iterate through each widget, creating a RemoteViews object and
        //applying the modified RemoteViews to each widget
        for(int widgetId:appWidgetIds){

            //Create a remote view
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_mensa);

            //TODO Update the UI
            views.setTextViewText(R.id.date, "2015-01-01");
            views.setTextViewText(R.id.meal1, "Essen 1");
            views.setTextViewText(R.id.meal2, "Essen 2");
            views.setTextViewText(R.id.meal3, "Essen 3");
            views.setTextViewText(R.id.meal4, "Essen 4");
            views.setTextViewText(R.id.meal5, "Essen 5");

            //Notify the App Widget Manager to update the widget using
            //the modified remote view.
            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        //TODO Handle deletion of the widget
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        //TODO Widget has been disabled
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context) {
        //TODO Widget has been enabled
        super.onEnabled(context);
    }
}
