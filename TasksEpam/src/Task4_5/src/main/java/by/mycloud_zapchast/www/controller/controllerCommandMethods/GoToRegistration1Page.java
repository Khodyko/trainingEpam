package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.mycloud_zapchast.www.controller.Command;
import by.mycloud_zapchast.www.service.ItemService;
import by.mycloud_zapchast.www.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToRegistration1Page implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final ItemService ITEM_SERVICE = PROVIDER.getItemService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/registration1.jsp";
		HttpSession session = request.getSession(true);
		List<String> depoDb = new ArrayList();

		try {
			depoDb = ITEM_SERVICE.getDepos();
		} catch (Exception e) {
			System.out.println("message: " + e.getMessage());
			e.printStackTrace();

		}

		request.setAttribute("deposDb", depoDb);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
}
