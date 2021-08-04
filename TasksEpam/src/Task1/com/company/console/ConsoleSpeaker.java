package Task1.com.company.console;


import Task1.com.company.controller.Validator;
import Task1.com.company.controller.commands.Command;
import Task1.com.company.controller.exception.InsertException;
import Task1.com.company.controller.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleSpeaker {
	Controller controller=Controller.getInstance();
	private static ConsoleSpeaker instance;
	Validator validator=Validator.getInstance();
	InsertManager insertManager=InsertManager.getInstance();
	private ConsoleSpeaker() {}
	public static ConsoleSpeaker getInstance() {
		if (instance == null) {
            instance = new ConsoleSpeaker();
        }
        return instance;
}


	public void showMessage(String message) {
		System.out.println(message);
	}
	public void showListMessage(String message){
		System.out.println("<<<<<"+message+">>>>>");
	}


	public Command showHeaderAndItems2Choice(String headingTitle , LinkedHashMap<String, Command> numberAndStr2Choice){

		System.out.println(headingTitle);

		for(Map.Entry<String, Command> entry: numberAndStr2Choice.entrySet()){
			System.out.println(entry.getKey());
		}

		String userInsert= insertManager.InsertAndTrim(); //get Exception
		if(userInsert==null || userInsert.equals("")) {
			new InsertException("You insert nothing, please, insert number of any command");
			userInsert = insertManager.InsertAndTrim(); //get Exception
		}
		if(!validator.isNumber(userInsert)){
			new InsertException("You insert: ", userInsert);
		}
		if(Integer.parseInt(userInsert)>numberAndStr2Choice.size() || Integer.parseInt(userInsert)<0){
			new InsertException("You insert wrong count:",Integer.parseInt(userInsert));
			userInsert= insertManager.InsertAndTrim(); //get Exception
		}




		controller.setLastCommand((Command) numberAndStr2Choice.values().toArray()[Integer.parseInt(userInsert)-1]); // for return with exception
		return (Command) numberAndStr2Choice.values().toArray()[Integer.parseInt(userInsert)-1];

	}









	
	
}
