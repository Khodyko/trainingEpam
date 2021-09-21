package by.hodyko.www.controller.controllerCommandMethods;

import java.io.IOException;

import by.hodyko.www.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class GoToRegistrationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/registration.jsp";
		String lastCommandName = "REGISTRATION_PAGE";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		request.getSession(true).setAttribute("lastURL", lastCommandName);
		requestDispatcher.forward(request, response);
	}

}
