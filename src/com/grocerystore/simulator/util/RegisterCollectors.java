package com.grocerystore.simulator.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.grocerystore.simulator.entity.Customer;
import com.grocerystore.simulator.entity.Register;

public class RegisterCollectors {

	private List<Register> registers = new ArrayList<>();

	public RegisterCollectors(int registerCount) {

		for (int index = 0; index < registerCount; index++) {
			registers.add(new Register(index));
		}
	}

	public List<Register> getRegisters() {
		return registers;
	}

	/**
	 * Customer Type A always chooses the register with the shortest line
	 * (fewest number of customers in line)
	 * 
	 * @return
	 */
	public Register findShortRegisterBySize() {
		List<Register> shortRegister = new ArrayList<>(registers);
		Collections.sort(registers, new RegisterCollectionSizeComparator());

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
	public Register findRegisterLeastItemAtEnd() {

		List<Register> emptyRegister = new ArrayList<>();
		Map<Customer, Register> custRegMapping = new HashMap<>();
		List<Customer> leastItemCustomer = new ArrayList<>();

		for (Register register : registers) {

			if (register.getQueue().size() == 0) {
				emptyRegister.add(register);
			} else {

				Customer lastCustomer = fetchLastElement(register.getQueue());
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

	private Customer fetchLastElement(Queue<Customer> customerQueue) {

		Customer lastCustomer = null;
		Iterator<Customer> iterator = customerQueue.iterator();
		while (iterator.hasNext()) {
			lastCustomer = iterator.next();
		}
		return lastCustomer;
	}
}
