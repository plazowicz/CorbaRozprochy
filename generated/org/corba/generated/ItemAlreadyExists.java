package org.corba.generated;


/**
* org/corba/generated/ItemAlreadyExists.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* Thursday, April 18, 2013 9:03:41 PM CEST
*/

public final class ItemAlreadyExists extends org.omg.CORBA.UserException
{

  public ItemAlreadyExists ()
  {
    super(ItemAlreadyExistsHelper.id());
  } // ctor


  public ItemAlreadyExists (String $reason)
  {
    super(ItemAlreadyExistsHelper.id() + "  " + $reason);
  } // ctor

} // class ItemAlreadyExists
