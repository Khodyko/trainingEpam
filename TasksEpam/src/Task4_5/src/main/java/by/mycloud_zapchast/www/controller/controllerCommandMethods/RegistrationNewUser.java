package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import by.mycloud_zapchast.www.controller.Command;
import by.mycloud_zapchast.www.entity.RegistrationInfo;
import by.mycloud_zapchast.www.entity.User;
import by.mycloud_zapchast.www.service.ItemService;
import by.mycloud_zapchast.www.service.ServiceException;
import by.mycloud_zapchast.www.service.ServiceProvider;
import by.mycloud_zapchast.www.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationNewUser implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final UserService USER_SERVICE = PROVIDER.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "GO_TO_AUTHORIZATION_PAGE";
		String message=null;
		String name = null;
		String secondName = null;
		String email = null;
		String password = null;
		Integer nameDepo = null;
		String role = null;
		Integer sector = null;
		name = request.getParameter("name");
		secondName = request.getParameter("second_name");
		email = request.getParameter("email");
		password = request.getParameter("password");
		
		role = "user";
		try {
			nameDepo = Integer.parseInt(request.getParameter("depo"));
		} catch (NumberFormatException e) {
			message = "предприятие не выбрано";
			path = "/WEB-INF/jsp/registration1.jsp";
			request.setAttribute("user_message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			return;
		}
		try {
			sector = Integer.parseInt(request.getParameter("sector"));
		} catch (NumberFormatException e) {
			path = "/WEB-INF/jsp/registration2.jsp";
			message = "сектор не выбран";
			request.setAttribute("user_message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			return;
		}
		
		
		RegistrationInfo registrationInfo = new RegistrationInfo(name, secondName, password, role, email, nameDepo,
				sector);
		try {
			
			USER_SERVICE.registerUser(registrationInfo);

		} catch (ServiceException e) {
			path = "/WEB-INF/jsp/registration2.jsp";
			message = e.getMessage();
			request.setAttribute("user_message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			return;
		}

		response.sendRedirect("Controller?commandToController=" + path);
	}
	public String encodeUTF8(String message) {
		return URLEncoder.encode(message, StandardCharsets.UTF_8);
	}

}
