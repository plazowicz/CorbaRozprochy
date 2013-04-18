package org.corba.server;

import org.corba.generated.ItemBOperations;

public class ItemBImpl extends ItemImpl implements ItemBOperations {

	public ItemBImpl(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float actionB(String a) {
		System.out.println("Get string: "+a);
		return 0.1f;
	}

}
