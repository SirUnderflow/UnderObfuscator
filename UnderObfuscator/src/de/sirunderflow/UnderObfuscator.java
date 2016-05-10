package de.sirunderflow;

import de.sirunderflow.obfuscation.UClass;

public class UnderObfuscator {
	
	public static UnderObfuscator instance = null;
	
	public static void main(String[] args) {
		(new UnderObfuscator()).init(args);
	}
	
	private void setupPaths(String[] args) {
		try {
			UClass uclass = new UClass("com.test.test");
			try {
				System.out.println(uclass.toString());
				uclass.addIntegerField("Feld", "13");;
				uclass.save();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
		}
	}
	
	
	private void init(String[] args) {
		UnderObfuscator.instance = this;
		this.setupPaths(args);
	}
}
