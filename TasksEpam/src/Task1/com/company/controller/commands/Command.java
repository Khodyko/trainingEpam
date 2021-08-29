package Task1.com.company.controller.commands;

import Task1.com.company.controller.Controller;

import java.util.LinkedHashMap;

public abstract class Command {
	public LinkedHashMap<String, Command> nameOfChoiceAndCommand = new LinkedHashMap<>();
	public Controller controller = Controller.getInstance();

	public abstract void execute();
}
