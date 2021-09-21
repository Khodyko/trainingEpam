package by.hodyko.www.controller.controllerCommandMethods;

import java.io.IOException;

import by.hodyko.www.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UnknownCommand implements Command {
	
	String lastCommandName = "UNKNOWN_COMMAND";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path;
		HttpSession session=request.getSession(false);
//		System.out.println("It is unknown command to Controller in running");
		path="/WEB-INF/jsp/unknownPage.jsp";
		session.setAttribute("lastURL", lastCommandName ); //for redirect in localization
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

}
