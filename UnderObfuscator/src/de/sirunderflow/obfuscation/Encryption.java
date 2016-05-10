package de.sirunderflow.obfuscation;

public class Encryption {
	
	private String key;
	private String Input;
	
	public Encryption(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getInput() {
		return Input;
	}

	public void setInput(String input) {
		Input = input;
	}
	
	public String getEncryptedResult() {
		String result = "";
		char[] chars = this.Input.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			result += String.valueOf((int) chars[i]) + "%";
		}
		return result;
	}
}
