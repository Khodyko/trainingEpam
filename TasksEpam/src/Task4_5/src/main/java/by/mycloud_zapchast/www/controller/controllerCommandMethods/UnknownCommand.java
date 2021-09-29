package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;

import by.mycloud_zapchast.www.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 * All unexpected situations are realised there
 * 
 * 
 * @author Vitamin_XO
 *
 */

public class UnknownCommand implements Command {
	
	String lastCommandName = "UNKNOWN_COMMAND";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path;
		path="/WEB-INF/jsp/error.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

}
