package org.corba.generated;


/**
* org/corba/generated/ItemBusy.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* Thursday, April 18, 2013 9:03:41 PM CEST
*/

public final class ItemBusy extends org.omg.CORBA.UserException
{

  public ItemBusy ()
  {
    super(ItemBusyHelper.id());
  } // ctor


  public ItemBusy (String $reason)
  {
    super(ItemBusyHelper.id() + "  " + $reason);
  } // ctor

} // class ItemBusy
