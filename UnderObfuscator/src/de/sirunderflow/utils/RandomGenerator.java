package de.sirunderflow.utils;

import java.util.Random;

public class RandomGenerator {
	
	private static Random rnd = new Random();
	
	public static String rndString(int length) {
		String result = "";
		char[] chars = "qwertzuiopasdfghjklyxcvbnmQWERTZUIOPASDFGHJKLYXCVBNM1234567890".toCharArray();
		for (int i = 0; i < length; i++) {
			result += String.valueOf(chars[rnd.nextInt(chars.length)]);
		}
		return "V" + result;
	}
}
