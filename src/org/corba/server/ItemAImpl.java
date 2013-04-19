package org.corba.server;

import org.omg.CORBA.IntHolder;

import MiddlewareTestbed.ItemAPOA;

public class ItemAImpl extends ItemAPOA {

	private String name;
	private long age;
	
	public ItemAImpl(String name) {
		this.name = name;
		this.age = System.currentTimeMillis();
	}

	@Override
	public void actionA(float a, IntHolder b) {
		System.out.println("get float: "+a);
		b.value = (int)a;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public int get_item_age() {
		return (int)(System.currentTimeMillis()-age);
	}
	
}
