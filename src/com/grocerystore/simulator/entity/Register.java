package com.grocerystore.simulator.entity;

import java.util.LinkedList;
import java.util.Queue;

public class Register implements Comparable<Register> {

	private Queue<Customer> queue = null;
	
	//There are two type of Register. Regular & Trainee
	// index == 0 -> Trainee
	// index == any positive number -> Regular
	private Integer index = null;
	
	public Register(Integer index) {
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
	public int compareTo(Register otherRegister) {

		return this.getIndex().compareTo(otherRegister.getIndex());
	}

}
