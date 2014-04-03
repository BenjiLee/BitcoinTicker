package com.chris.bitcointicker;

import java.io.IOException;

import com.chris.price.GetPrice;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class main extends Activity{
	
	ImageButton image;
	TextView tickerText;
	
	private GetPrice blockchain = new GetPrice();
	DownloadTicker usd = new DownloadTicker();  
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image = (ImageButton) findViewById(R.id.btnTicker);
		tickerText = (TextView) findViewById(R.id.btnTickerText);
		usd.execute(new String[] {""});
		
	}
	
	public void btnUpdateTicker(View v) {
		DownloadTicker usd = new DownloadTicker(); 
		usd.execute(new String[] {""});
	}
	
	private class DownloadTicker extends AsyncTask<String, Void, String> {

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
		protected void onPostExecute(String result){
			tickerText.setText(result);
		}
		
	}
}
