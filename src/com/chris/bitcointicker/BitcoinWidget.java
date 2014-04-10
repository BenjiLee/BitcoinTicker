package com.chris.bitcointicker;

import java.io.IOException;

import com.chris.price.GetPrice;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

public class BitcoinWidget extends AppWidgetProvider {

	TextView widgetText;
	RemoteViews remoteViews;
	String price;
	DownloadTicker usd;

	private GetPrice blockchain = new GetPrice();
	public static String updateTicker = "update the ticker";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.bitcoinwidget);
		remoteViews.setTextViewText(R.id.WidgetTextView, price);

		Intent active = new Intent(context, BitcoinWidget.class);
		active.setAction(updateTicker);
		PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context,
				0, active, 0);
		remoteViews
				.setOnClickPendingIntent(R.id.WidgetBtn, actionPendingIntent);
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(updateTicker)) {
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.bitcoinwidget);
			DownloadTicker usd = new DownloadTicker(remoteViews, context);
			usd.execute(new String[] { "" });

//			AppWidgetManager.getInstance(context).updateAppWidget(
//					new ComponentName(context, BitcoinWidget.class),
//					remoteViews);
		}
		super.onReceive(context, intent);

	}

	public class DownloadTicker extends AsyncTask<String, Void, String> {
		private RemoteViews views;
		private Context context;

		public DownloadTicker(RemoteViews remoteViews, Context p) {
			views = remoteViews;
			context = p;
		}

		@Override
		protected String doInBackground(String... arg0) {
			try {
				return blockchain.GetPrice();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "err";
			}
		}

		@Override
		protected void onPostExecute(String result) {
			Log.w("myApp", "Onpostr" + result);
			views.setTextViewText(R.id.WidgetTextView, result);
			AppWidgetManager.getInstance(context).updateAppWidget(
					new ComponentName(context, BitcoinWidget.class),
					views);
		}

	}

}