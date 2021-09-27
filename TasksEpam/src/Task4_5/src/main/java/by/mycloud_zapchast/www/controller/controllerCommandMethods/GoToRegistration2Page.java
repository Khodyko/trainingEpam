package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.mycloud_zapchast.www.controller.Command;
import by.mycloud_zapchast.www.entity.Sector;
import by.mycloud_zapchast.www.service.ItemService;
import by.mycloud_zapchast.www.service.ServiceException;
import by.mycloud_zapchast.www.service.ServiceProvider;
import by.mycloud_zapchast.www.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * Go to second and last page of registration
 * @author Vitamin_XO
 *
 */
public class GoToRegistration2Page implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final ItemService ITEM_SERVICE = PROVIDER.getItemService();
	private static final UserService USER_SERVICE = PROVIDER.getUserService();
	private static final Logger LOGGER=LogManager.getLogger();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = null;
		String path = "/WEB-INF/jsp/registration2.jsp";
		HttpSession session = request.getSession(true);
		Integer chosenDepo = null;
		String writenName = null;
		String writen2Name = null;
	
		try {
			/**Getting of depo id written on the 1st registration page*/
			chosenDepo = Integer.parseInt(request.getParameter("depo"));

		} catch (NumberFormatException e) {
			path = "GO_TO_REGISTRATION_1_PAGE";
			message = "Выберите предприятие";
			LOGGER.warn("command " +request.getAttribute("commandToController")+" "+e.getStackTrace());
			response.sendRedirect("Controller?commandToController=" + path + "&user_message=" + encodeUTF8(message));
			return;
		}
		/**Getting of params written on the 1st registration page*/
		writenName = request.getParameter("name");
		writen2Name = request.getParameter("second_name");

	
		List<Sector> sectors = null;
		try {
			USER_SERVICE.validate1Registration(writenName, writen2Name);
		} catch (ServiceException e) {
			path = "/WEB-INF/jsp/registration1.jsp";
			message = e.getMessage();
			LOGGER.warn("command " +request.getAttribute("commandToController")+" "+e.getStackTrace());
			request.setAttribute("user_message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			return;
		}
		try {
			/**Getting sectors list belong to depo*/
			sectors = ITEM_SERVICE.getSectors(chosenDepo);
		} catch (ServiceException e) {
			path = "ERROR_PAGE";
			message = e.getMessage();
			LOGGER.warn("command " +request.getAttribute("commandToController")+" "+e.getStackTrace());
			response.sendRedirect("Controller?commandToController=" + path + "&user_message=" + encodeUTF8(message));
			return;
		}
		/**Throw params into ui*/
		request.setAttribute("depo", chosenDepo);
		request.setAttribute("name", writenName);
		request.setAttribute("second_name", writen2Name);
		request.setAttribute("sectorsDb", sectors);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		/**Go into registration2.jsp*/
		requestDispatcher.forward(request, response);
	}

	/**Encoder*/
	public String encodeUTF8(String message) {
		return URLEncoder.encode(message, StandardCharsets.UTF_8);
	}
}
