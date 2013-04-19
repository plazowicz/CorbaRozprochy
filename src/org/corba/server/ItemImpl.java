package org.corba.server;

import org.corba.generated.ItemPOA;

public class ItemImpl extends ItemPOA {

	private String name;
	private long age;
	
	public ItemImpl(String name) {
		this.name = name;
		this.age = System.currentTimeMillis();
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
