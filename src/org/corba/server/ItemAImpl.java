package org.corba.server;

import org.corba.generated.ItemAOperations;
import org.omg.CORBA.IntHolder;

public class ItemAImpl extends ItemImpl implements ItemAOperations {

	public ItemAImpl(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionA(float a, IntHolder b) {
		System.out.println("get float: "+a);
		b.value = (int)a;
	}
	
}
