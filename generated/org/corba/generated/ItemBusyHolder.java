package org.corba.generated;

/**
* org/corba/generated/ItemBusyHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* Thursday, April 18, 2013 9:03:41 PM CEST
*/

public final class ItemBusyHolder implements org.omg.CORBA.portable.Streamable
{
  public org.corba.generated.ItemBusy value = null;

  public ItemBusyHolder ()
  {
  }

  public ItemBusyHolder (org.corba.generated.ItemBusy initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.corba.generated.ItemBusyHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.corba.generated.ItemBusyHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.corba.generated.ItemBusyHelper.type ();
  }

}
