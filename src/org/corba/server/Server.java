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

	private String host;
	private String port;
	
	public Server() {
		
	}
	
	public void start(String[] args) throws InvalidName, AdapterInactive, ServantAlreadyActive, WrongPolicy, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed, ServantNotActive {
		
		//load args
//		String[] args = loadArgs();
		
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
		org.omg.CORBA.Object nsRef = orb.string_to_object("IOR:010000002b00000049444c3a6f6d672e6f72672f436f734e616d696e672f4e616d696e67436f6e746578744578743a312e300000010000000000000074000000010102000f0000003134392e3135362e39372e3135350000c05a00000b0000004e616d6553657276696365000300000000000000080000000100000000545441010000001c000000010000000100010001000000010001050901010001000000090101000354544108000000af428b4f0100022c");
	
		//narrow name service ref
		NamingContextExt ncRef = NamingContextExtHelper.narrow(nsRef);
		
		// bind obj ref in naming
		NameComponent path[] = ncRef.to_name("AFactory");
		ncRef.rebind(path, rootpoa.servant_to_reference(factory));
				
		// wait for invocations from clients
		orb.run();
	}
	
	private String[] loadArgs() {
		try {
			loadServerConf();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		FileInputStream fis = null;
		Properties prop = new Properties();
		try {
			prop.load(fis = new FileInputStream("conf/nameservice.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if( prop.containsKey("name") ) {
			return new String[]{"-ORBInitialPort",port,"-ORBServerHost",host,"NameService="+prop.getProperty("name")};
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
			return new String[]{"-ORBInitialPort",port,"-ORBServerHost",host,"NameService="+ior};
		}
		else if( prop.containsKey("ior") ) {
			return new String[]{"-ORBInitialPort",port,"-ORBServerHost",host,"NameService="+prop.getProperty("ior")};
		}
		return new String[]{};
	}
	
	private void loadServerConf() throws FileNotFoundException, IOException {
		FileInputStream fis = null;
		Properties prop = new Properties();
		prop.load(fis = new FileInputStream("conf/server.properties"));
		if( prop.containsKey("port"))
			port = prop.getProperty("port");
		if( prop.containsKey("host"))
			host = prop.getProperty("host");
		fis.close();
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.start(args);
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AdapterInactive e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServantAlreadyActive e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPolicy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotProceed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServantNotActive e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
