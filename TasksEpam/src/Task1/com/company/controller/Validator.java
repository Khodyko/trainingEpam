package Task1.com.company.controller;

import java.util.regex.Pattern;

public class Validator {
	private static Validator instance;

	private Validator() {
	}

	public static Validator getInstance() {
		if (instance == null) {
			instance = new Validator();
		}
		return instance;
	}

	public boolean isInsertPunktsValid(String message, Integer numberOfVariants) {

		if (isPositiveAndIsNumber(message) && !message.equals("") && message != null
				&& numberOfVariants <= Integer.parseInt(message)) {
			System.out.println("message is valid");
			return true;
		} else {
			return false;
		}

	}

	public boolean isPositiveAndIsNumber(String string) {
		if (isNumber(string) && Integer.parseInt(string) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNumber(String string) {
		String pattern = "^[0-9]+$";
		if (Pattern.matches(pattern, string)) {
			return true;
		} else {
			return false;
		}
	}

}
