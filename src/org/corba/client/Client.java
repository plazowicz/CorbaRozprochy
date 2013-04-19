package org.corba.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ShortHolder;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import MiddlewareTestbed.AFactory;
import MiddlewareTestbed.AFactoryHelper;
import MiddlewareTestbed.Item;
import MiddlewareTestbed.ItemA;
import MiddlewareTestbed.ItemAHelper;
import MiddlewareTestbed.ItemAlreadyExists;
import MiddlewareTestbed.ItemB;
import MiddlewareTestbed.ItemBHelper;
import MiddlewareTestbed.ItemBusy;
import MiddlewareTestbed.ItemC;
import MiddlewareTestbed.ItemCHelper;
import MiddlewareTestbed.ItemNotExists;

public class Client {

	private static final Logger logger = Logger.getLogger(Client.class.getSimpleName());
	
	private AFactory factory;
	private ORB orb;
	
	public Client() {
		
	}
	
	public void init(String[] args) throws InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
//		String[] args = loadArgs();
		orb = ORB.init(args,null);
		// get the Name Service Object Reference from IOR
		

		org.omg.CORBA.Object nsRef = orb.string_to_object("IOR:010000002b00000049444c3a6f6d672e6f72672f436f734e616d696e672f4e616d696e67436f6e746578744578743a312e300000010000000000000074000000010102000f0000003134392e3135362e39372e3135350000c05a00000b0000004e616d6553657276696365000300000000000000080000000100000000545441010000001c000000010000000100010001000000010001050901010001000000090101000354544108000000af428b4f0100022c");

				// narrow NS reference
		NamingContextExt ncRef = NamingContextExtHelper.narrow( nsRef );

		org.omg.CORBA.Object objRef = ncRef.resolve_str("AFactory");
			
		factory = AFactoryHelper.narrow(objRef);
	}
	
	public Item createItem(String name,String type) {
		Item item = null;
		try {
			item = factory.create_item(name, type);
		} catch (ItemAlreadyExists e) {
			logger.severe(e.getMessage());
		}
		return item;
	}
	
	public void releaseItem(String name) {
		try {
			factory.release_item(name);
		} catch (ItemNotExists e) {
			logger.severe(e.getMessage());
		}
	}
	
	public Item takeItem(String name) {
		Item item = null;
		try {
			item = factory.take_item(name);
		} catch (ItemNotExists e) {
			logger.severe(e.getMessage());
		} catch (ItemBusy e) {
			logger.severe(e.getMessage());
		}
		return item;
	}
	
	public void doStuff(Item item) {
		System.out.println("doStuff");
		if(item._is_a("IDL:MiddlewareTestbed/ItemC:1.0")){
			System.out.println("wszedlem?");
			ItemC itc = ItemCHelper.narrow(item);
			System.out.println(itc.name());
			IntHolder a = new IntHolder();
			a.value = 5;
			ShortHolder b = new ShortHolder();
			b.value = 5;
			System.out.println("Wrzucam: "+a.value+" "+b.value);
			itc.actionC(a, b);
			System.out.println("Dostaje: "+a.value+" "+b.value);
			System.out.println("zyjem: "+itc.get_item_age());
		}else if(item._is_a("IDL:MiddlewareTestbed/ItemA:1.0")){
			
			ItemA ita = ItemAHelper.narrow(item);
			System.out.println(ita.name());
			IntHolder b = new IntHolder();
			b.value = 5;
			Float a = new Float(1.4);
			b.value = 5;
			System.out.println("Wrzucam: "+a+" "+b.value);
			ita.actionA(a, b);
			System.out.println("Dostaje: "+b.value);
			System.out.println("zyjem: "+ita.get_item_age());

		}else if(item._is_a("IDL:MiddlewareTestbed/ItemB:1.0")){
			
			ItemB itb = ItemBHelper.narrow(item);
			System.out.println(itb.name());
			System.out.println("Wynik: "+itb.actionB("ala ma kota"));
			System.out.println("zyjem: "+itb.get_item_age());

		}	
	}
	
	public void start() {
		Scanner in = new Scanner(System.in);
		String name, type;
		Item item;
		while( true ) {
			System.out.println("Please choose what you'd like to do:");
			System.out.println("\t- (c)reate item");
			System.out.println("\t- (a)ction on item");
			System.out.println("\t- (q)uit");
			try {
				switch( (char)System.in.read() ) {
					case 'c':
						System.in.read();
						System.out.println("Please choose which item you'd like to create: A, B or C");
						type = in.nextLine();
						System.out.println("Please give item name");
						name = in.nextLine();
						item = createItem(name,type);
						if( item != null ) {
							System.out.println("Item successfully created");
							doStuff(item);
							releaseItem(name);
						} else {
							System.out.println("Creation failed");
						}
						break;
					case 'a':
						System.in.read();
						System.out.println("Please give item name");
						name = in.nextLine();
						item = takeItem(name);
						if( item != null ) {
							System.out.println("Item took");
							doStuff(item);
							releaseItem(name);
						} 
						else {
							System.out.println("action failed");
						}
						
						break;
					case 'q':
						System.in.read();
						System.out.println("Quit.");
						System.exit(0);
					default:
						System.in.read();
						System.out.println("Wrong option");
						System.exit(-1);
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
	}
	
	public String[] loadArgs() {
		Properties prop = new Properties();
		FileInputStream is = null;
		try {
			is = new FileInputStream("conf/client.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new String[]{};
		
		
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		try {
			client.init(args);
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotProceed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.start();
	}
	
}
