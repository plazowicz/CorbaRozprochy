package org.corba.server;

import org.corba.generated.ItemCOperations;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ShortHolder;

public class ItemCImpl extends ItemImpl implements ItemCOperations {

	public ItemCImpl(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionC(IntHolder a, ShortHolder b) {
		a.value = 5;
		b.value = 4;
	}

}
