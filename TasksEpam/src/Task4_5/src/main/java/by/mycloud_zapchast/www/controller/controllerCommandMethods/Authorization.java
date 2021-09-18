package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;

import by.mycloud_zapchast.www.controller.Command;
import by.mycloud_zapchast.www.entity.User;
import by.mycloud_zapchast.www.service.ServiceException;
import by.mycloud_zapchast.www.service.ServiceProvider;
import by.mycloud_zapchast.www.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Authorization implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final UserService USER_SERVICE = PROVIDER.getUserService();


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/standart_pre_search.jsp";
		String writenEmail=request.getParameter("email");
		String writenPassword=request.getParameter("password");
		HttpSession session = request.getSession(true);
		
		try {
			User user= USER_SERVICE.authorizeUser(writenEmail, writenPassword);
			session = request.getSession(true);

		    session.setAttribute("user_session", user);
		    System.out.println(user);
		} catch (ServiceException e) {
			System.out.println("hueston we have a problem");
			e.printStackTrace();
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

}
