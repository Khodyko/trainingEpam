package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		StandartSearchItem itemBd = new StandartSearchItem();
		List<StandartSearchItem> standartSearchItemList = new ArrayList<StandartSearchItem>();
		HttpSession session = request.getSession(true);
		List<String> yearDb = new ArrayList();
		String name = request.getParameter("itemName");
		String nn = request.getParameter("nn");
		String nnSap = request.getParameter("nnSap");
		itemBd.setName(name);
		itemBd.setNn(nn);
		itemBd.setNnSap(nnSap);

		try {
			standartSearchItemList = ITEM_SERVICE.getStandartSearchItem(itemBd);
		} catch (ServiceException e) {
			path = "/WEB-INF/jsp/error.jsp";
			System.out.println("message: " + e.getMessage());
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
		}
		request.setAttribute("items_bd", standartSearchItemList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

}
