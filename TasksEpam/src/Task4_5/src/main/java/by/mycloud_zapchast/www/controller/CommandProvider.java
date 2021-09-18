package by.mycloud_zapchast.www.controller;

import java.util.HashMap;
import java.util.Map;

import by.mycloud_zapchast.www.controller.controllerCommandMethods.ApplicationSearch;
import by.mycloud_zapchast.www.controller.controllerCommandMethods.Authorization;
import by.mycloud_zapchast.www.controller.controllerCommandMethods.GoToApplicationPreSearch;
import by.mycloud_zapchast.www.controller.controllerCommandMethods.GoToAuthorizationPage;
import by.mycloud_zapchast.www.controller.controllerCommandMethods.GoToDocumentation;
import by.mycloud_zapchast.www.controller.controllerCommandMethods.GoToStandartPreSearch;
import by.mycloud_zapchast.www.controller.controllerCommandMethods.GoToRegistration1Page;
import by.mycloud_zapchast.www.controller.controllerCommandMethods.GoToRegistration2Page;
import by.mycloud_zapchast.www.controller.controllerCommandMethods.RegistrationNewUser;
import by.mycloud_zapchast.www.controller.controllerCommandMethods.SignOut;
import by.mycloud_zapchast.www.controller.controllerCommandMethods.StandartSearch;
import by.mycloud_zapchast.www.controller.controllerCommandMethods.UnknownCommand;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		
		commands.put(CommandName.GO_TO_STANDART_PRE_SEARCH, new GoToStandartPreSearch());
		commands.put(CommandName.STANDART_SEARCH, new StandartSearch());
		commands.put(CommandName.GO_TO_APPLICATION_PRE_SEARCH, new GoToApplicationPreSearch());
		commands.put(CommandName.APPLICATION_SEARCH, new ApplicationSearch());
		commands.put(CommandName.GO_TO_DOCUMENTATION, new GoToDocumentation());
		commands.put(CommandName.GO_TO_REGISTRATION_1_PAGE, new GoToRegistration1Page());
		commands.put(CommandName.GO_TO_REGISTRATION_2_PAGE, new GoToRegistration2Page());
		commands.put(CommandName.REGISTRATION_NEW_USER, new RegistrationNewUser());
		commands.put(CommandName.GO_TO_AUTHORIZATION_PAGE, new GoToAuthorizationPage());
		commands.put(CommandName.AUTHORIZATION, new Authorization());
		commands.put(CommandName.SIGN_OUT, new SignOut());
		commands.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
			}

	public Command findCommand(String name) {
		System.out.println(name);
		if (name == null) {
			name = CommandName.UNKNOWN_COMMAND.toString();
		}

		CommandName commandName;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException e) { // logging
			commandName = CommandName.UNKNOWN_COMMAND;
		}

		Command command = commands.get(commandName);
		return command;
	}

}
