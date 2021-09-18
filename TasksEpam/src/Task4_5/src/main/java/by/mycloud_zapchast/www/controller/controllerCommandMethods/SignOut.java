package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;

import by.mycloud_zapchast.www.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/authorization.jsp";
		HttpSession session=request.getSession(true);
		session.removeAttribute("user_session");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
		
	}
	
}
