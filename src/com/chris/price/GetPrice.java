package com.chris.price;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetPrice {
	
	public String GetPrice() throws IOException {
		String USDInfo = getUSDInfo();
		String price = getLastPrice(USDInfo);
		return price;		
	}

	public String getUSDInfo() throws IOException {
		URL url = new URL("http://blockchain.info/ticker");
		URLConnection conn = url.openConnection();
		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			if (line.indexOf("USD") != -1){
				return line;
			}
		}
		return "none";
	}
	
	public String getLastPrice(String USD) {
		USD = USD.split("last\" : ")[1];
		USD = USD.split(", \"buy\"")[0];
		return USD;
	}
}
