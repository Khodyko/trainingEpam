package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.util.List;

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

public class GoToRegistration2Page implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final ItemService ITEM_SERVICE = PROVIDER.getItemService();
	private static final UserService USER_SERVICE = PROVIDER.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message=null;
		String path = "/WEB-INF/jsp/registration2.jsp";
		HttpSession session = request.getSession(true);
		Integer chosenDepo=null;
		String writenName  =null;
		String writen2Name =null;
		try {
		chosenDepo = Integer.parseInt(request.getParameter("depo"));}
		catch(NumberFormatException e) {
			path="GO_TO_REGISTRATION_1_PAGE";
			message="Выберите предприятие в поле выбора";
			request.setAttribute("user_message", message);
			response.sendRedirect("Controller?commandToController=" + path);
		}
		writenName = request.getParameter("name");
		writen2Name = request.getParameter("second_name");
		
		
		request.setAttribute("depo", chosenDepo);
		request.setAttribute("name", writenName);
		request.setAttribute("second_name", writen2Name);
		List<Sector> sectors = null;
//		try{
//			
//		}catch (ServiceException e) {
//			System.out.println("message: " + e.getMessage());
//			e.printStackTrace();
//		}
		try {
			sectors = ITEM_SERVICE.getSectors(chosenDepo);
		} catch (ServiceException e) {
			System.out.println("message: " + e.getMessage());
			e.printStackTrace();
		}

		request.setAttribute("sectorsDb", sectors);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
}
