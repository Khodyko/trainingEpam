package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;

import by.mycloud_zapchast.www.controller.Command;
import by.mycloud_zapchast.www.entity.RegistrationInfo;
import by.mycloud_zapchast.www.entity.User;
import by.mycloud_zapchast.www.service.ItemService;
import by.mycloud_zapchast.www.service.ServiceException;
import by.mycloud_zapchast.www.service.ServiceProvider;
import by.mycloud_zapchast.www.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationNewUser implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final UserService USER_SERVICE = PROVIDER.getUserService();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lastCommandName = "REGISTRATION_PAGE";
		String name = request.getParameter("name");
		String secondName=request.getParameter("second_name");
		String email=request.getParameter("email");
		String password = request.getParameter("password");
		Integer nameDepo=Integer.parseInt(request.getParameter("depo"));
		String role="user";
		Integer sector=Integer.parseInt(request.getParameter("sector"));
		
		//validation!!!!!
		RegistrationInfo registrationInfo = new RegistrationInfo(name, secondName, password, role, email, nameDepo, sector);
		System.out.println(registrationInfo);
		try {
			USER_SERVICE.registerUser(registrationInfo);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("Controller?commandToController=GO_TO_AUTHORIZATION_PAGE");
	}

}
