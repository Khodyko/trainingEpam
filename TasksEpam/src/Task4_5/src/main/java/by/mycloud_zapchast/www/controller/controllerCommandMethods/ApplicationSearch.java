package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.mycloud_zapchast.www.controller.Command;
import by.mycloud_zapchast.www.entity.AppSearchItem;
import by.mycloud_zapchast.www.entity.User;
import by.mycloud_zapchast.www.service.ItemService;
import by.mycloud_zapchast.www.service.ServiceException;
import by.mycloud_zapchast.www.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Search with summ of sector's application in current depo and depo's application in chosen year
 * (AppSearchItem) 
 * PageCounterFilter Before this command 
 * SecurityFilter Before
 * this command (user must have a session)
 * @author Vitamin_XO
 */

public class ApplicationSearch implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final ItemService ITEM_SERVICE = PROVIDER.getItemService();
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/application_search.jsp";

		List<AppSearchItem> AppSearchItemList = new ArrayList<AppSearchItem>();
		String message = null;
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user_session");
		List<String> yearDb = new ArrayList();

		/** Get nn, name, nnSap, year from UI. */
		String name = request.getParameter("itemName");
		String nn = request.getParameter("nn");
		String nnSap = request.getParameter("nnSap");
		String year = request.getParameter("year");

		/** Add AppSearchItem and throw it to DB */
		AppSearchItem itemFromUI = new AppSearchItem();
		itemFromUI.setName(name);
		itemFromUI.setNn(nn);
		itemFromUI.setNnSap(nnSap);
		itemFromUI.setYear(year);
		/**Get current page from PageCounterFilter (pagination)*/
		Integer currentPageNumber = (Integer) request.getAttribute("currentPage");

		try {
			/** Getting of Item List */
			AppSearchItemList = ITEM_SERVICE.getAppSearchItem(itemFromUI, user, currentPageNumber);
		} catch (ServiceException e) {
			path = "/WEB-INF/jsp/error.jsp";
			message = e.getMessage();
			LOGGER.warn("command " + request.getAttribute("commandToController") + " " + e.getStackTrace());
			request.setAttribute("user_message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			return;
		}
		try {
			/** Get YearList for a yeaField(UI) */
			yearDb = ITEM_SERVICE.getYears();
		} catch (Exception e) {
			path = "/WEB-INF/jsp/error.jsp";
			message = e.getMessage();
			request.setAttribute("user_message", message);
			LOGGER.warn("command " + request.getAttribute("commandToController") + " " + e.getStackTrace());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			return;
		}
		request.setAttribute("item_bd_list", AppSearchItemList);
		/** (all the params throws one more time for pagination working) */
		request.setAttribute("yearsDb", yearDb);
		request.setAttribute("item_search", itemFromUI);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		/** Go into application_search.jsp */
		requestDispatcher.forward(request, response);
	}

}
