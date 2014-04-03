package com.chris.bitcointicker;

import java.io.IOException;

import com.chris.price.GetPrice;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;


public class main extends Activity{
	
	TextView showResult;
	private GetPrice blockchain = new GetPrice();
	DownloadTicker usd = new DownloadTicker();  
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		showResult = (TextView) findViewById(R.id.textView1);
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
			showResult.setText(result);
		}
		
	}
}
