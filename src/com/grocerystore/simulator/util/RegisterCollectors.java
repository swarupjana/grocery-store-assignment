package com.grocerystore.simulator.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.grocerystore.simulator.entity.Customer;
import com.grocerystore.simulator.entity.RegisterCounter;
import com.grocerystore.simulator.type.CustomerType;

public class RegisterCollectors {

	private List<RegisterCounter> registers = new ArrayList<>();

	public RegisterCollectors(int registerCount) {

		for (int index = 0; index < registerCount; index++) {
			registers.add(new RegisterCounter(index));
		}
	}

	public List<RegisterCounter> getRegisters() {
		return registers;
	}

	/**
	 * Customer Type A always chooses the register with the shortest line
	 * (fewest number of customers in line)
	 * 
	 * @return
	 */
	public RegisterCounter findShortRegisterBySize() {
		List<RegisterCounter> shortRegister = new ArrayList<>();
		for (RegisterCounter registerCounter : registers) {
			shortRegister.add(registerCounter);
		}
		Collections.sort(shortRegister, new RegisterCollectionSizeComparator());

		return shortRegister.get(0);
	}

	/**
	 * Customer Type B looks at the last customer in each line, and always
	 * chooses to be behind the customer with the fewest number of items left to
	 * check out, regardless of how many other customers are in the line or how
	 * many items they have. Customer Type B will always choose an empty line
	 * before a line with any customers in it.
	 * 
	 * @return
	 */
	public RegisterCounter findRegisterLeastItemAtEnd() {

		List<RegisterCounter> emptyRegister = new ArrayList<>();
		Map<Customer, RegisterCounter> custRegMapping = new HashMap<>();
		List<Customer> leastItemCustomer = new ArrayList<>();

		for (RegisterCounter register : registers) {

			if (register.getQueue().size() == 0) {
				emptyRegister.add(register);
			} else {

				Customer lastCustomer = fetchLastCustomerElement(register.getQueue());
				custRegMapping.put(lastCustomer, register);
				leastItemCustomer.add(lastCustomer);
			}

		}

		if (emptyRegister.size() > 0) {
			return emptyRegister.get(0);
		} else {
			Collections.sort(leastItemCustomer);
			return custRegMapping.get(leastItemCustomer.get(0));
		}

	}

	private Customer fetchLastCustomerElement(Queue<Customer> customerQueue) {

		Customer lastCustomer = null;
		Iterator<Customer> iterator = customerQueue.iterator();
		while (iterator.hasNext()) {
			lastCustomer = iterator.next();
		}
		return lastCustomer;
	}

	public boolean isRegisterServing() {

		for (RegisterCounter registerCounter : registers) {
			if (registerCounter.getQueue().size() != 0) {
				return true;
			}
		}
		return false;
	}

	public void serveCustomer(List<Customer> customerListArrivedSameTime) {

		for (Customer customer : customerListArrivedSameTime) {
			if(customer.getType().equals(CustomerType.A)){
				RegisterCounter shortRegisterCount = findShortRegisterBySize();
				shortRegisterCount.getQueue().offer(customer);
			} else {
				RegisterCounter registerwithleastItems = findRegisterLeastItemAtEnd();
                registerwithleastItems.getQueue().offer(customer);
			}
		}
	}
}
