package Task1.com.company.controller.exception;

import Task1.com.company.controller.Controller;

public class InsertException extends Exception {
	Controller controller = Controller.getInstance();

	public InsertException(String message) {
		System.err.println(message);
		controller.getLastCommand().execute();
	}

	public InsertException() {
		System.err.println("Something wrong");
	}

	public InsertException(String message, Integer count) {
		System.err.println(message + count + " please, insert valid count");
		controller.getLastCommand().execute();
	}

	public InsertException(String message, String wrongInsert) {
		System.err.println(message + " \"" + wrongInsert + "\" please, insert valid count");
		controller.getLastCommand().execute();
	}

}
