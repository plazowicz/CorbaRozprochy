package MiddlewareTestbed;

/**
* org/corba/generated/AFactoryHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* Thursday, April 18, 2013 9:03:41 PM CEST
*/

public final class AFactoryHolder implements org.omg.CORBA.portable.Streamable
{
  public MiddlewareTestbed.AFactory value = null;

  public AFactoryHolder ()
  {
  }

  public AFactoryHolder (MiddlewareTestbed.AFactory initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = MiddlewareTestbed.AFactoryHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    MiddlewareTestbed.AFactoryHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return MiddlewareTestbed.AFactoryHelper.type ();
  }

}
