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
		String path = "GO_TO_STANDART_PRE_SEARCH";
		final String WRITEN_EMAIL = request.getParameter("email");
		final String WRITEN_PASSWORD_1 = request.getParameter("password1");
		String message=null;
		HttpSession session = request.getSession(true);

		try {
			User user = USER_SERVICE.authorizeUser(WRITEN_EMAIL, WRITEN_PASSWORD_1);
			session.setAttribute("user_session", user);
			System.out.println(user);
		} catch (ServiceException e) {
			message = e.getMessage();
			e.printStackTrace(); // logger
			
			request.setAttribute("user_message", message);
			path = "/WEB-INF/jsp/authorization.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			return;
		}

		response.sendRedirect("Controller?commandToController=" + path);
	}

}
