package de.sirunderflow;

import de.sirunderflow.obfuscation.Obfuscation;
import de.sirunderflow.obfuscation.UClass;

public class UnderObfuscator {
	
	public static UnderObfuscator instance = null;
	
	public static void main(String[] args) {
		(new UnderObfuscator()).init(args);
	}
	
	private void setupPaths(String[] args) {
		
	}
	
	
	private void init(String[] args) {
		UnderObfuscator.instance = this;
		this.setupPaths(args);
	
		try {
			Obfuscation obf = new Obfuscation(new UClass("com.test.test"), "");
			obf.encryptAllVariables();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
