package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.mail.handlers.message_rfc822;

import by.mycloud_zapchast.www.controller.Command;
import by.mycloud_zapchast.www.entity.AppSearchItem;
import by.mycloud_zapchast.www.entity.StandartSearchItem;
import by.mycloud_zapchast.www.service.ItemService;
import by.mycloud_zapchast.www.service.ServiceException;
import by.mycloud_zapchast.www.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Search in item library by properties of items
 * PageCounterFilter Before this command 
 * @author Vitamin_XO
 *
 */
public class StandartSearch implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final ItemService ITEM_SERVICE = PROVIDER.getItemService();
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/standart_search.jsp";
		String message = null;
		List<StandartSearchItem> standartSearchItemList = new ArrayList<StandartSearchItem>();
		/** Get params from UI */
		String name = request.getParameter("itemName");
		String nn = request.getParameter("nn");
		String nnSap = request.getParameter("nnSap");
		StandartSearchItem itemFromUI = new StandartSearchItem();
		/** Set params from UI into StandartSearchItem */
		itemFromUI.setName(name);
		itemFromUI.setNn(nn);
		itemFromUI.setNnSap(nnSap);
		/**Get current page from PageCounterFilter (pagination)*/
		Integer currentPageNumber = (Integer) request.getAttribute("currentPage");
		try {
			/**Get standartSearchItem List from DB*/
			standartSearchItemList = ITEM_SERVICE.getStandartSearchItem(itemFromUI, currentPageNumber);
		} catch (ServiceException e) {
			path = "/WEB-INF/jsp/error.jsp";
			message = e.getMessage();
			LOGGER.warn("command " + request.getAttribute("commandToController") + " " + e.getStackTrace());
			request.setAttribute("user_message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			return;
		}
		/**Set searching item list*/
		request.setAttribute("item_bd_list", standartSearchItemList);
		/**Repeat use of searching data in pagination*/
		request.setAttribute("item_search", itemFromUI);
		/**Go to standart_page.jsp*/
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
}
