package com.test;

public class test {
	
	private String Var1 = "Maus";
	private int Var2 = 133;
	public double Var3 = 222D;
	
	public void haus() {
		System.out.println(Var1 + Var2 + Var3);
	}

	public static void main(String[] args) {
		(new test()).zwiebel();
	}
	
	public void zwiebel() {
		this.haus();
	}
	
	public String lol() {
		return "TExt";
	}
	
	
}
