package MiddlewareTestbed;


/**
* org/corba/generated/ItemBHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* Thursday, April 18, 2013 9:03:41 PM CEST
*/

abstract public class ItemBHelper
{
  private static String  _id = "IDL:MiddlewareTestbed/ItemB:1.0";

  public static void insert (org.omg.CORBA.Any a, MiddlewareTestbed.ItemB that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static MiddlewareTestbed.ItemB extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (MiddlewareTestbed.ItemBHelper.id (), "ItemB");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static MiddlewareTestbed.ItemB read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ItemBStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, MiddlewareTestbed.ItemB value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static MiddlewareTestbed.ItemB narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof MiddlewareTestbed.ItemB)
      return (MiddlewareTestbed.ItemB)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      MiddlewareTestbed._ItemBStub stub = new MiddlewareTestbed._ItemBStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static MiddlewareTestbed.ItemB unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof MiddlewareTestbed.ItemB)
      return (MiddlewareTestbed.ItemB)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      MiddlewareTestbed._ItemBStub stub = new MiddlewareTestbed._ItemBStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
