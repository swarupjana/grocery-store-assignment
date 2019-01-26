package com.grocerystore.simulator.util;

import java.util.Comparator;

import com.grocerystore.simulator.entity.RegisterCounter;

public class RegisterCollectionSizeComparator implements Comparator<RegisterCounter> {

	@Override
	public int compare(RegisterCounter register1, RegisterCounter register2) {

		Integer size1 = register1.getQueue().size();
		Integer size2 = register2.getQueue().size();
		
		return size1.compareTo(size2);
	}

}
