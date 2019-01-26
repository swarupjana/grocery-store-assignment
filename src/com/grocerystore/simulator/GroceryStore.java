package com.grocerystore.simulator;

import java.util.LinkedList;
import java.util.Queue;

import com.grocerystore.simulator.entity.Customer;
import com.grocerystore.simulator.util.GroceryUtils;
import com.grocerystore.simulator.util.RegisterCollectors;

public class GroceryStore {

	private Queue<Customer> customerQueue = new LinkedList<>();
	private RegisterCollectors registerCollectors = null;

	public GroceryStore(RegisterCollectors registerCollectors) {
		super();
		this.registerCollectors = registerCollectors;
	}

	public RegisterCollectors getRegisterCollectors() {
		return registerCollectors;
	}

	public Queue<Customer> getCustomerQueue() {
		return customerQueue;
	}
	
	public int calculateTime() {
		return 0;
	}

	public static void main(String[] args) {

		GroceryStore groceryStore = GroceryUtils.initialize(args);
		int totalTime = groceryStore.calculateTime();
		
		System.out.println("Finished at: t=" + totalTime + " minutes");
	}

	

}
