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

public class GoToRegistration2Page implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final ItemService ITEM_SERVICE = PROVIDER.getItemService();
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/registration2.jsp";
		HttpSession session = request.getSession(true);
		String chosenDepo =request.getParameter("depo");
		String writenName=request.getParameter("name");
		String writen2Name=request.getParameter("second_name");
		request.setAttribute("depo", chosenDepo);
		request.setAttribute("name", writenName);
		request.setAttribute("second_name", writen2Name);
		List<String> sectors=null;
		try {
			sectors = ITEM_SERVICE.getSectors(chosenDepo);
		} catch (Exception e) {
			System.out.println("message: " + e.getMessage());
			e.printStackTrace();
			
		}
		
		
		request.setAttribute("sectorsDb", sectors);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
}
