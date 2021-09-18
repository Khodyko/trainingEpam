package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class ApplicationSearch implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final ItemService ITEM_SERVICE = PROVIDER.getItemService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/standart_search.jsp";
		AppSearchItem itemBd = new AppSearchItem();
		List<AppSearchItem> baseItemList=new ArrayList<AppSearchItem>();
		HttpSession session = request.getSession(true);
		User user=(User) session.getAttribute("user_session");
		List<String> yearDb = new ArrayList();
		String name = request.getParameter("itemName");
		String nn = request.getParameter("nn");
		String nnSap = request.getParameter("nnSap");
		String year = request.getParameter("year");
		itemBd.setName(name);
		itemBd.setNn(nn);
		itemBd.setNnSap(nnSap);
		itemBd.setYear(year);
		Integer userId=user.getId_user();

		try {
			baseItemList = ITEM_SERVICE.getAppSearchItem(itemBd, userId);
		} catch (ServiceException e) {
			System.out.println("message: " + e.getMessage());
			e.printStackTrace();
		}
		try {
			yearDb = ITEM_SERVICE.getYears();
		} catch (Exception e) {
			System.out.println("message: " + e.getMessage());
			e.printStackTrace();
			
		}
		System.out.println(baseItemList);
		request.setAttribute("yearsDb", yearDb);
		request.setAttribute("items_bd", baseItemList );
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

}
