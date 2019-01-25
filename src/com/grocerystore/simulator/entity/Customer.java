package com.grocerystore.simulator.entity;

import com.grocerystore.simulator.type.CustomerType;

public class Customer implements Comparable<Customer> {

	private Integer itemCount;
	private CustomerType type;
	private int timeArrived;

	
	public Customer(Integer itemCount, CustomerType type, int timeArrived) {
		super();
		this.itemCount = itemCount;
		this.type = type;
		this.timeArrived = timeArrived;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public int getTimeArrived() {
		return timeArrived;
	}

	public void setTimeArrived(int timeArrived) {
		this.timeArrived = timeArrived;
	}

	/**
	 * Compare based on item count.
	 * If same then compare on Customer Type.
	 */
	@Override
	public int compareTo(Customer otherCutomer) {
		int compareResult = 0;
		compareResult = this.getItemCount().compareTo(otherCutomer.getItemCount());

		if (compareResult == 0) {
			compareResult = this.getType().compareTo(otherCutomer.getType());

		}
		return compareResult;
	}

}
