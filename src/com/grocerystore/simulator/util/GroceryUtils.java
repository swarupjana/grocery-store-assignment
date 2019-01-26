package com.grocerystore.simulator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.grocerystore.simulator.GroceryStore;
import com.grocerystore.simulator.entity.Customer;
import com.grocerystore.simulator.type.CustomerType;

public class GroceryUtils {

	public static GroceryStore initialize(String[] args) {

		GroceryStore groceryStore = null;
		RegisterCollectors registerCollectors = null;
		BufferedReader reader = null;

		String line = "";
		boolean isFirstLine = true;

		try {
			reader = new BufferedReader(new FileReader(new File(args[0])));
		} catch (FileNotFoundException e) {
			System.out.println("File not found-->" + e.getMessage());
			// Terminate the Program
			System.exit(-1);
		}

		try {
			while ((line = reader.readLine()) != null) {

				if (isFirstLine) {
					try {
						int noOfRegisterCounter = Integer.parseInt(line);
						registerCollectors = new RegisterCollectors(noOfRegisterCounter);
						groceryStore = new GroceryStore(registerCollectors);
					} catch (NumberFormatException e) {
						System.out.println("Error in parsing number of registers->" + e.getMessage());
						// Terminate the Program
						System.exit(-1);
					}

				} else {

					Customer customer = buildCustomerForGrocery(line);
					groceryStore.getCustomerQueue().offer(customer);
				}
			}
		} catch (IOException e) {
			System.out.println("Exception in reading the file" + e.getMessage());
			// abnormal termination
			System.exit(-1);
		}

		return null;
	}

	private static Customer buildCustomerForGrocery(String line) {

		String[] contents = line.split(" ");
		if (contents.length < 3) {
			System.out.println("Error in Input-->" + line);
			// Terminate the Program
			System.exit(-1);
		}

		try {
			int timeArrived = Integer.parseInt(contents[1]);
			int itemCount = Integer.parseInt(contents[2]);

			if (contents[0].equalsIgnoreCase(CustomerType.A.toString())) {
				return new Customer(CustomerType.A, timeArrived, itemCount);
			} else if (contents[0].equalsIgnoreCase(CustomerType.B.toString())) {
				return new Customer(CustomerType.B, timeArrived, itemCount);
			} else {
				System.out.println("Customer Type is Invalid");
				// Terminate the Program
				System.exit(-1);
			}
		} catch (NumberFormatException e) {
			System.out.println("Error in parsing customer contents" + e.getMessage());
			// Terminate the Program
			System.exit(-1);
		}

		return null;
	}

}
