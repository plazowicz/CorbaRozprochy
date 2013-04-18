package org.corba.server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;


public class Server {

	public Server() {
		
	}
	
	public void start() throws InvalidName, AdapterInactive, ServantAlreadyActive, WrongPolicy, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed, ServantNotActive {
		
		//load args
		String[] args = loadArgs();
		
		//create and initialize ORB
		ORB orb = ORB.init(args,null);
		// get reference to rootpoa 
		POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		//activate poa manager
		rootpoa.the_POAManager().activate();
		
		//create factory
		AFactoryImpl factory = new AFactoryImpl(rootpoa);
		
		//register factory with POA
		rootpoa.activate_object(factory);
		
		//get name service ref
		org.omg.CORBA.Object nsRef = orb.resolve_initial_references("NameService");
	
		//narrow name service ref
		NamingContextExt ncRef = NamingContextExtHelper.narrow(nsRef);
		
		// bind obj ref in naming
		NameComponent path[] = ncRef.to_name("AFactory");
		ncRef.rebind(path, rootpoa.servant_to_reference(factory));
				
		// wait for invocations from clients
		orb.run();
	}
	
	public String[] loadArgs() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("conf/nameservice.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if( prop.containsKey("name") ) {
			return new String[]{"NameService="+prop.getProperty("name")};
		}
		else if( prop.containsKey("url") ) {
			InputStream is = null;
			try {
				is = new URL(prop.getProperty("url")).openStream();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ior = null;
			try {
				ior = new BufferedReader(new InputStreamReader(is)).readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new String[]{"NameService="+ior};
		}
		else if( prop.containsKey("ior") ) {
			return new String[]{"NameService="+prop.getProperty("ior")};
		}
		return new String[]{};
	}
}
