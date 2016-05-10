package de.sirunderflow;

import de.sirunderflow.obfuscation.Obfuscation;
import de.sirunderflow.obfuscation.UClass;

public class UnderObfuscator {
	
	public static UnderObfuscator instance = null;
	
	public static void main(String[] args) {
		(new UnderObfuscator()).init();
	}
	
	private void init() {
		UnderObfuscator.instance = this;
		
		try {
			UClass uclass = new UClass("com.test.test");
			Obfuscation obf = new Obfuscation(uclass, "test");
			obf.encryptAllStrings();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
