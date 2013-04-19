package org.corba.server;

import MiddlewareTestbed.ItemBPOA;

public class ItemBImpl extends ItemBPOA {

	private String name;
	private long age;
	
	public ItemBImpl(String name) {
		this.name = name;
		this.age = System.currentTimeMillis();
	}

	@Override
	public float actionB(String a) {
		System.out.println("Get string: "+a);
		return 0.1f;
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
