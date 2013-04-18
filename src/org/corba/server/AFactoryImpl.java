package org.corba.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.corba.generated.AFactoryPOA;
import org.corba.generated.Item;
import org.corba.generated.ItemAHelper;
import org.corba.generated.ItemAlreadyExists;
import org.corba.generated.ItemBHelper;
import org.corba.generated.ItemBusy;
import org.corba.generated.ItemCHelper;
import org.corba.generated.ItemHelper;
import org.corba.generated.ItemNotExists;
import org.corba.util.Tuple;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class AFactoryImpl extends AFactoryPOA {

	private Lock createLock = new ReentrantLock();
	private Lock takeLock = new ReentrantLock();
	private Lock releaseLock = new ReentrantLock();
	private POA poa;
	private Map<String,Tuple<Item,Boolean>> storage;
	
	public AFactoryImpl(POA poa) {
		this.poa = poa;
		storage = new HashMap<String,Tuple<Item,Boolean>>();
	}
	
	@Override
	public Item create_item(String name, String type) throws ItemAlreadyExists {
		Item item = null;
		createLock.lock();
		try{
			if( storage.containsKey(name) )
				throw new ItemAlreadyExists();
			if( type.equalsIgnoreCase("A")) {
				item = ItemAHelper.narrow(poa.servant_to_reference(new ItemAImpl(name)));
			}
			else if( type.equalsIgnoreCase("B")) {
				item = ItemBHelper.narrow(poa.servant_to_reference(new ItemBImpl(name)));
			}
			else if(type.equalsIgnoreCase("C")) {
				item = ItemCHelper.narrow(poa.servant_to_reference(new ItemCImpl(name)));
			}
			else {
				item = ItemHelper.narrow(poa.servant_to_reference(new ItemImpl(name)));
			}
			storage.put(name, new Tuple<Item,Boolean>(item,true));
			return item;
		} catch (ServantNotActive e) {
			e.printStackTrace();
		} catch (WrongPolicy e) {
			e.printStackTrace();
		} finally {
			createLock.unlock();
		}
		return null;
	}

	@Override
	public Item take_item(String name) throws ItemNotExists, ItemBusy {
		Tuple<Item,Boolean> t = null;
		takeLock.lock();
		try {
			if( (t = storage.get(name)) == null )
				throw new ItemNotExists();
			if( t.second )
				throw new ItemBusy();
			t.second = true;
			return t.first;	 
		} finally {
			takeLock.unlock();
		}
	}

	@Override
	public void release_item(String name) throws ItemNotExists {
		Tuple<Item,Boolean> t = null;
		releaseLock.lock();
		try {
			if( (t = storage.get(name)) == null )
				throw new ItemNotExists();
			t.second = false;
		} finally {
			releaseLock.unlock();
		}
	}

}
