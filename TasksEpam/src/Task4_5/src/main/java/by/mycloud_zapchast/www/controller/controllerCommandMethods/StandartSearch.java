package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class StandartSearch implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final ItemService ITEM_SERVICE = PROVIDER.getItemService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/standart_search.jsp";
		StandartSearchItem itemFromUI = new StandartSearchItem();
		List<StandartSearchItem> standartSearchItemList = new ArrayList<StandartSearchItem>();
		String message=null;
		String name = request.getParameter("itemName");
		String nn = request.getParameter("nn");
		String nnSap = request.getParameter("nnSap");
		itemFromUI.setName(name);
		itemFromUI.setNn(nn);
		itemFromUI.setNnSap(nnSap);
		System.out.println("itemFromUI: "+itemFromUI);
		//get from PageCounterFilter
		Integer currentPageNumber=(Integer) request.getAttribute("currentPage");
		try {
			standartSearchItemList = ITEM_SERVICE.getStandartSearchItem(itemFromUI, currentPageNumber);
		} catch (ServiceException e) {
			path = "/WEB-INF/jsp/error.jsp";
			message=e.getMessage();
			e.printStackTrace();
			request.setAttribute("user_message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			return;
		}
		
		request.setAttribute("item_bd_list", standartSearchItemList);
		request.setAttribute("item_search", itemFromUI);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
}
