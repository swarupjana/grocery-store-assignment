package com.grocerystore.simulator.entity;

import java.util.LinkedList;
import java.util.Queue;

public class RegisterCounter implements Comparable<RegisterCounter> {

	private Queue<Customer> queue = null;
	
	//There are two type of Register. Regular & Trainee
	// index == last index -> Trainee
	// index == any positive number apart from last index -> Regular
	private Integer index = null;
	
	public RegisterCounter(Integer index) {
		super();
		this.queue = new LinkedList<>();
		this.index = index;
	}



	public Queue<Customer> getQueue() {
		return queue;
	}



	public Integer getIndex() {
		return index;
	}



	@Override
	public int compareTo(RegisterCounter otherRegister) {

		return this.getIndex().compareTo(otherRegister.getIndex());
	}

}
