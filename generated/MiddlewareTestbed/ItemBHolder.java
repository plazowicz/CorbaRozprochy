package MiddlewareTestbed;

/**
* org/corba/generated/ItemBHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* Thursday, April 18, 2013 9:03:41 PM CEST
*/

public final class ItemBHolder implements org.omg.CORBA.portable.Streamable
{
  public MiddlewareTestbed.ItemB value = null;

  public ItemBHolder ()
  {
  }

  public ItemBHolder (MiddlewareTestbed.ItemB initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = MiddlewareTestbed.ItemBHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    MiddlewareTestbed.ItemBHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return MiddlewareTestbed.ItemBHelper.type ();
  }

}
