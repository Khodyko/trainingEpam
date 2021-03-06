package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.mycloud_zapchast.www.controller.Command;
import by.mycloud_zapchast.www.entity.Depo;
import by.mycloud_zapchast.www.service.ItemService;
import by.mycloud_zapchast.www.service.ServiceException;
import by.mycloud_zapchast.www.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 * Go to first registration page
 * 
 * @author Vitamin_XO
 *
 */
public class GoToRegistration1Page implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final ItemService ITEM_SERVICE = PROVIDER.getItemService();
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/registration1.jsp";
		String encodedMessage = null;
		List<Depo> depoDb = null;
		String message = null;
		try {
			/** Getting of depos List for UI field */
			depoDb = ITEM_SERVICE.getDepos();
		} catch (ServiceException e) {
			path = "/WEB-INF/jsp/error.jsp";
			message = e.getMessage();
			request.setAttribute("user_message", message);
			LOGGER.warn("command " + request.getAttribute("commandToController") + " " + e.getStackTrace());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			return;
		}

		request.setAttribute("deposDb", depoDb);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		/** Go into registration 1 page */
		requestDispatcher.forward(request, response);
	}
}
