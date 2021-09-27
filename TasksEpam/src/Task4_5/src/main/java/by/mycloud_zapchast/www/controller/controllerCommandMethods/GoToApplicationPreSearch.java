package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.mycloud_zapchast.www.controller.Command;
import by.mycloud_zapchast.www.service.ItemService;
import by.mycloud_zapchast.www.service.ServiceProvider;
import by.mycloud_zapchast.www.service.impl.MailSenderImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * plug before Application page
 * @author Vitamin_XO
 * 
 **/

public class GoToApplicationPreSearch implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final ItemService ITEM_SERVICE = PROVIDER.getItemService();
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/application_pre_search.jsp";
		HttpSession session = request.getSession(true);
		List<String> yearDb = new ArrayList();
		String message = null;
		
		try {
			/**get years list for a year field*/
			yearDb = ITEM_SERVICE.getYears();
		} catch (Exception e) {
			path = "/WEB-INF/jsp/error.jsp";
			message = e.getMessage();
			LOGGER.warn("command " + request.getAttribute("commandToController") + " " + e.getStackTrace());
			request.setAttribute("user_message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
		}
		
		request.setAttribute("yearsDb", yearDb);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		/**Go to application_pre_search.jsp*/
		requestDispatcher.forward(request, response);
	}

}
