package by.hodyko.www.controller.controllerCommandMethods;

import java.io.IOException;

import by.hodyko.www.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAuthorizationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/authorization.jsp";
		String lastCommandName = "AUTHORIZATION_PAGE";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		request.getSession(true).setAttribute("lastURL", lastCommandName); // for redirect in localization
		requestDispatcher.forward(request, response);
	}

}
