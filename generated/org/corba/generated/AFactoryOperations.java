package org.corba.generated;


/**
* org/corba/generated/AFactoryOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* Thursday, April 18, 2013 9:03:41 PM CEST
*/

public interface AFactoryOperations 
{
  org.corba.generated.Item create_item (String name, String type) throws org.corba.generated.ItemAlreadyExists;
  org.corba.generated.Item take_item (String name) throws org.corba.generated.ItemNotExists, org.corba.generated.ItemBusy;
  void release_item (String name) throws org.corba.generated.ItemNotExists;
} // interface AFactoryOperations
