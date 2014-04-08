package com.chris.bitcointicker;

import java.io.IOException;

import com.chris.price.GetPrice;

import android.appwidget.AppWidgetProvider;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

public class BitcoinWidget extends AppWidgetProvider {

	TextView WidgetTextViewTicker;
	
	private GetPrice blockchain = new GetPrice();
	DownloadTicker usd = new DownloadTicker(); 
	
	public void WidgetBtn(View v) {
		WidgetTextViewTicker.setText("result");
		DownloadTicker usd = new DownloadTicker(); 
		usd.execute(new String[] {""});
	}
	
	private class DownloadTicker extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... arg0) {
			try {
				return blockchain.GetPrice();
			} catch (IOException e) {
				e.printStackTrace();
				return "err";
			}
		}
		@Override
		protected void onPostExecute(String result){
			WidgetTextViewTicker.setText(result);
		}
		
	}
	

}