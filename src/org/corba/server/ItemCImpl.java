package org.corba.server;

import org.corba.generated.ItemCPOA;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ShortHolder;


public class ItemCImpl extends ItemCPOA {

	private String name;
	private long age;
	
	public ItemCImpl(String name) {
		this.name = name;
		this.age = System.currentTimeMillis();
	}

	@Override
	public void actionC(IntHolder a, ShortHolder b) {
		a.value = 5;
		b.value = 4;
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
