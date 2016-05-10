package com.test;

public class test {
	
	private String Var1 = "Maus";
	private String Var2 = "Haus";
	private String Var3 = "Baum";
	
	public void haus() {
		System.out.println(Var1);
		System.out.println(Var2);
		System.out.println(Var3);
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
