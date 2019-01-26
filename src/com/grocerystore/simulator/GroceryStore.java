package com.grocerystore.simulator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.grocerystore.simulator.entity.Customer;
import com.grocerystore.simulator.entity.RegisterCounter;
import com.grocerystore.simulator.util.GroceryUtils;
import com.grocerystore.simulator.util.RegisterCollectors;

public class GroceryStore {

	private Queue<Customer> customerQueue = new LinkedList<>();
	private RegisterCollectors registerCollectors = null;

	public GroceryStore(RegisterCollectors registerCollectors) {
		this.registerCollectors = registerCollectors;
	}

	public RegisterCollectors getRegisterCollectors() {
		return registerCollectors;
	}

	public Queue<Customer> getCustomerQueue() {
		return customerQueue;
	}

	public int calculateTime() {

		int time = 1;
		RegisterCollectors registerCollectors = this.getRegisterCollectors();
		List<RegisterCounter> registerCounterList = registerCollectors.getRegisters();
		Queue<Customer> customerQueue = this.getCustomerQueue();
		List<Customer> customerListArrivedSameTime = null;

		while (!customerQueue.isEmpty() || registerCollectors.isRegisterServing()) {
			customerListArrivedSameTime = GroceryUtils.fetchCustomerSameTime(customerQueue, time);

			/**
			 * If two or more customers arrive at the same time, those with
			 * fewer items choose registers before those with more,
			 */
			Collections.sort(customerListArrivedSameTime);
			registerCollectors.serveCustomer(customerListArrivedSameTime);
			
			int index = 0;
			while(index < registerCounterList.size())
			{
				Queue<Customer> customerList = registerCounterList.get(index).getQueue();
				if(index == registerCounterList.size() - 1){
					GroceryUtils.traineeServe(customerList);
				} else {
					GroceryUtils.regularServe(customerList);
				}
				index++;
			}
			time++;

		}
		return time;
	}

	public static void main(String[] args) {

		GroceryStore groceryStore = GroceryUtils.initialize(args);
//		String [] input = {"resources/input5.txt"};
//		GroceryStore groceryStore = GroceryUtils.initialize(input);
		int totalTime = groceryStore.calculateTime();

		System.out.println("Finished at: t=" + totalTime + " minutes");
	}

}
