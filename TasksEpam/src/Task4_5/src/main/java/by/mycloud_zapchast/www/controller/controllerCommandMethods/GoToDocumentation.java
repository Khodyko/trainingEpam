package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;

import by.mycloud_zapchast.www.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Documentation is not realised yet
 * @author Vitamin_XO
 *
 */

public class GoToDocumentation implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "WEB-INF/jsp/documentation.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
		
	}

}
