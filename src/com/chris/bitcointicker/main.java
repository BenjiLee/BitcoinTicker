package com.chris.bitcointicker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.chris.bitcointicker.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;


public class main extends Activity{
	
	TextView showResult;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try{
			Document doc = Jsoup.connect("https://bitcoinity.org/markets/").get();
			showResult = (TextView) findViewById(R.id.textView1);
			Elements divs = doc.select("div#last_prices");
			showResult.setText(divs.text());
		}
		catch (Exception e){
			System.out.println(e);		
		}
	}
}
