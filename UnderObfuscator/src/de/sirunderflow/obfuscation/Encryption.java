package de.sirunderflow.obfuscation;

import de.sirunderflow.utils.RandomGenerator;

public class Encryption {
	
	private String key;
	private Object Input;
	
	public Encryption(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getInput() {
		return Input;
	}

	public void setInput(Object input) {
		Input = input;
	}
	
	public String getEncryptedResult_Integer() {
		String result = "";
		for (int i = 0; i < (int) this.Input; i++) {
			result += RandomGenerator.rndString(1);
		}
		return result;
	}
	
	public String getEncryptedResult_String() {
		String result = "";
		char[] chars = String.valueOf(this.Input).toCharArray();
		for (int i = 0; i < chars.length; i++) {
			result += String.valueOf((int) chars[i]) + "%";
		}
		return result;
	}
}
