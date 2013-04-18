package org.corba.util;

public class Tuple<T1,T2> {

	public T1 first;
	public T2 second;
	
	public Tuple(T1 t1, T2 t2) {
		first = t1;
		second = t2;
	}
	
	public static void main(String[] args) {
		Tuple<String,Integer> t = new Tuple<String,Integer>("ala",5);
		System.out.println(t.first);
		System.out.println(t.second);
	}
}
