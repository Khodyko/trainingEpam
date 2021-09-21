package by.hodyko.www.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.hodyko.www.controller.controllerCommandMethods.AddNews;
import by.hodyko.www.controller.controllerCommandMethods.AuthorizationUser;
import by.hodyko.www.controller.controllerCommandMethods.ChangeLocal;
import by.hodyko.www.controller.controllerCommandMethods.DeleteNews;
import by.hodyko.www.controller.controllerCommandMethods.GoToAddNewsPage;
import by.hodyko.www.controller.controllerCommandMethods.GoToAuthorizationPage;
import by.hodyko.www.controller.controllerCommandMethods.GoToConcreteNews;
import by.hodyko.www.controller.controllerCommandMethods.GoToMainPage;
import by.hodyko.www.controller.controllerCommandMethods.GoToRegistrationPage;
import by.hodyko.www.controller.controllerCommandMethods.GoToUpdateNewsPage;
import by.hodyko.www.controller.controllerCommandMethods.RegistrationNewUser;
import by.hodyko.www.controller.controllerCommandMethods.UnknownCommand;
import by.hodyko.www.controller.controllerCommandMethods.UpdateNews;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		
		commands.put(CommandName.AUTHORIZATION_PAGE, new GoToAuthorizationPage());
		commands.put(CommandName.REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.REGISTRATION_NEW_USER, new RegistrationNewUser());
		commands.put(CommandName.AUTHORIZATION_USER, new AuthorizationUser());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.ADD_NEWS, new AddNews());
		commands.put(CommandName.ADD_NEWS_PAGE, new GoToAddNewsPage());
		commands.put(CommandName.CHANGE_LOCAL, new ChangeLocal());
		commands.put(CommandName.GO_CONCRETE_NEWS, new GoToConcreteNews());
		commands.put(CommandName.DELETE_NEWS, new DeleteNews());
		commands.put(CommandName.UPDATE_NEWS_PAGE, new GoToUpdateNewsPage());
		commands.put(CommandName.UPDATE_NEWS, new UpdateNews());
		commands.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
	}

	public Command findCommand(String name) {
		Logger logger = LogManager.getLogger();
		if (name == null) {
			name = CommandName.UNKNOWN_COMMAND.toString();
		}

		CommandName commandName;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException e) {
			logger.warn("Unknown command to controller");
			commandName = CommandName.UNKNOWN_COMMAND;
		}

		Command command = commands.get(commandName);
		return command;
	}

}
