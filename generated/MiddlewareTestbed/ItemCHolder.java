package MiddlewareTestbed;

/**
* org/corba/generated/ItemCHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* Thursday, April 18, 2013 9:03:41 PM CEST
*/

public final class ItemCHolder implements org.omg.CORBA.portable.Streamable
{
  public MiddlewareTestbed.ItemC value = null;

  public ItemCHolder ()
  {
  }

  public ItemCHolder (MiddlewareTestbed.ItemC initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = MiddlewareTestbed.ItemCHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    MiddlewareTestbed.ItemCHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return MiddlewareTestbed.ItemCHelper.type ();
  }

}
