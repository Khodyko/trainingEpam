package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.mycloud_zapchast.www.controller.Command;
import by.mycloud_zapchast.www.entity.User;
import by.mycloud_zapchast.www.service.ServiceException;
import by.mycloud_zapchast.www.service.ServiceProvider;
import by.mycloud_zapchast.www.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 	Authorization by Email and Password
 * @author Vitamin_XO
 */
public class Authorization implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final UserService USER_SERVICE = PROVIDER.getUserService();
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "GO_TO_STANDART_PRE_SEARCH";
		String message = null;
		String encodedMessage = null;
		HttpSession session = request.getSession(true);

		/** Get written email and password */
		final String WRITEN_EMAIL = request.getParameter("email");
		final String WRITEN_PASSWORD = request.getParameter("password");

		try {
			/** get user, if data is valid */
			User user = USER_SERVICE.authorizeUser(WRITEN_EMAIL, WRITEN_PASSWORD);
			/** realize authorization */
			session.setAttribute("user_session", user);

		} catch (ServiceException e) {
			message = e.getMessage();
			LOGGER.warn("command " + request.getAttribute("commandToController") + " " + e.getStackTrace());
			path = "GO_TO_AUTHORIZATION_PAGE";
			encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
			response.sendRedirect("Controller?commandToController=" + path + "&user_message=" + encodedMessage);
			return;
		}
		/** Go to standart pre search page */
		response.sendRedirect("Controller?commandToController=" + path);
	}

}
