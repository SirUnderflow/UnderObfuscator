package com.test;

public class test {
	
	private String Var1 = "Maus";
	private String Var2 = "Haus";
	private String Var3 = "Baum";
	
	private int wert1 = 13;
	private int wert2 = 14;
	private int wert3 = 37;
	
	
	public void haus() {
		System.out.println("Strings: ");
		System.out.println(Var1);
		System.out.println(Var2);
		System.out.println(Var3);
		System.out.println("Integers: ");
		System.out.println(wert1);
		System.out.println(wert2);
		System.out.println(wert3);
	}
	  
	public static void main(String[] args) {
		(new test()).zwiebel();
	}
	
	public void zwiebel() {
		this.haus();
	}
	
	public String lol() {
		return "TEXT";
	}
	
	
}
