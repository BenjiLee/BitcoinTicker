package com.chris.test;

import java.io.IOException;

import junit.framework.TestCase;

import com.chris.price.GetPrice;

public class test extends TestCase {	
	
	private GetPrice blockchain = new GetPrice();

	public void testGetPrice() throws IOException {
		System.out.println("test USD price:" + blockchain.GetPrice());
		
	}
}
