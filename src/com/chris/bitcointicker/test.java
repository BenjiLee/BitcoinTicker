package com.chris.bitcointicker;

import java.io.IOException;
import com.chris.price.GetPrice;

import junit.framework.TestCase;

public class test extends TestCase {	
	
	private GetPrice blockchain = new GetPrice();

	public void testGetPrice() throws IOException {
		System.out.println("test USD price:" + blockchain.GetPrice());
		
	}
}
