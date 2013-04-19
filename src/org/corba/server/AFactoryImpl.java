package org.corba.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.corba.util.Tuple;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import MiddlewareTestbed.AFactoryPOA;
import MiddlewareTestbed.Item;
import MiddlewareTestbed.ItemAlreadyExists;
import MiddlewareTestbed.ItemBusy;
import MiddlewareTestbed.ItemHelper;
import MiddlewareTestbed.ItemNotExists;

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
		Servant servant = null;
		createLock.lock();
		try{
			if( storage.containsKey(name) )
				throw new ItemAlreadyExists();
			if( type.equalsIgnoreCase("A"))
				servant = new ItemAImpl(name);
			else if( type.equalsIgnoreCase("B"))
				servant = new ItemBImpl(name);
			else if(type.equalsIgnoreCase("C"))
				servant = new ItemCImpl(name);
			else
				servant = new ItemImpl(name);
			item = ItemHelper.narrow(poa.id_to_reference(poa.activate_object(servant)));
			storage.put(name, new Tuple<Item,Boolean>(item,true));
			System.out.println("Server: Item created");
			return item;
		} catch (WrongPolicy e) {
			e.printStackTrace();
		} catch (ObjectNotActive e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServantAlreadyActive e) {
			// TODO Auto-generated catch block
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
