package org.corba.generated;

/**
* org/corba/generated/ItemCHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* Thursday, April 18, 2013 9:03:41 PM CEST
*/

public final class ItemCHolder implements org.omg.CORBA.portable.Streamable
{
  public org.corba.generated.ItemC value = null;

  public ItemCHolder ()
  {
  }

  public ItemCHolder (org.corba.generated.ItemC initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.corba.generated.ItemCHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.corba.generated.ItemCHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.corba.generated.ItemCHelper.type ();
  }

}