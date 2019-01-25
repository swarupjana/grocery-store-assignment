package com.grocerystore.simulator.util;

import java.util.Comparator;

import com.grocerystore.simulator.entity.Register;

public class RegisterCollectionSizeComparator implements Comparator<Register> {

	@Override
	public int compare(Register register1, Register register2) {

		Integer size1 = register1.getQueue().size();
		Integer size2 = register2.getQueue().size();
		
		return size1.compareTo(size2);
	}

}
