package by.hodyko.www.controller.controllerCommandMethods;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.hodyko.www.bean.RegistrationInfo;
import by.hodyko.www.bean.RoleEnum;
import by.hodyko.www.controller.Command;
import by.hodyko.www.service.ServiceException;
import by.hodyko.www.service.ServiceProvider;
import by.hodyko.www.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationNewUser implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final UserService userService = PROVIDER.getUserService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path;
		String exceptionMessage="";
		String lastCommandName = "REGISTRATION_PAGE";
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		RoleEnum role = RoleEnum.STANDARD_USER;
		final Logger logger=LogManager.getLogger();

		if (login == null || login.equals("") || password == null || password.equals("")) {
			path = "REGISTRATION_PAGE&message=Some of fields are empty, please fill it";
			lastCommandName = "REGISTRATION_PAGE";
			logger.warn("lastcommand "+ lastCommandName+" "+"some of parameters of user-regeistration-input are empty");
			request.getSession(true).setAttribute("lastURL", lastCommandName); // for redirect in localization
			response.sendRedirect("Controller?commandToController=" + path);
			return;
		}
		
		RegistrationInfo info = new RegistrationInfo(login, password, role);
		
		try {
			userService.registration(info);
			request.setAttribute("message", "Registration complite, please log in");
			path = "AUTHORIZATION_PAGE&message=Registration complite, please log in";
			lastCommandName = "AUTHORIZATION_PAGE";
			request.getSession(true).setAttribute("lastURL", lastCommandName); // for redirect in localization
			response.sendRedirect("Controller?commandToController=" + path);
		} catch (ServiceException e) {
			logger.warn("lastcommand "+ lastCommandName+" "+e.getMessage());
			exceptionMessage=e.getMessage();
			path = "REGISTRATION_PAGE&message="+exceptionMessage;
			lastCommandName = "REGISTRATION_PAGE";
			request.getSession(true).setAttribute("lastURL", lastCommandName); // for redirect in localization
			response.sendRedirect("Controller?commandToController=" + path);
		}
	}
}
