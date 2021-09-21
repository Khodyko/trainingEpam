package by.hodyko.www.controller.controllerCommandMethods;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.hodyko.www.bean.RegistrationInfo;
import by.hodyko.www.bean.RoleEnum;
import by.hodyko.www.bean.User;
import by.hodyko.www.controller.Command;
import by.hodyko.www.service.ServiceException;
import by.hodyko.www.service.ServiceProvider;
import by.hodyko.www.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthorizationUser implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final UserService USER_SERVICE = PROVIDER.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path;
		String exceptionMessage = "";
		String lastCommandName = "AUTHORIZATION_PAGE";
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		RoleEnum role = RoleEnum.STANDARD_USER;
		Logger logger=LogManager.getLogger();

		if (login == null || login.equals("") || password == null || password.equals("")) {
			logger.warn("lastcommand "+ lastCommandName+" "+"some of fields of user-authorization-input are empty");
			path = "AUTHORIZATION_PAGE&message=Some of fields are empty, please fill it";
			request.getSession(true).setAttribute("lastURL", lastCommandName); // for redirect in localization
			response.sendRedirect("Controller?commandToController=" + path);
			return;
		}
		RegistrationInfo info = new RegistrationInfo(login, password, role);
		try {
			User user = USER_SERVICE.authorization(info);
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			lastCommandName = "GO_TO_MAIN_PAGE";
			session.setAttribute("role", user.getRole().toString());
			session.setAttribute("lastURL", lastCommandName); // for redirect in localization
			response.sendRedirect("Controller?commandToController=" + lastCommandName);

		} catch (ServiceException e) {
			exceptionMessage=e.getMessage();
			logger.warn("command " +lastCommandName+e.getMessage());
			path = "AUTHORIZATION_PAGE&message="+exceptionMessage;
			request.getSession(true).setAttribute("lastURL", lastCommandName); // for redirect in localization
			response.sendRedirect("Controller?commandToController=" + path);
		}
	}
}
