package by.hodyko.www.controller.controllerCommandMethods;

import java.io.IOException;

import by.hodyko.www.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocal implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute("local", request.getParameter("local"));
		
		String lastCommandName = (String) request.getSession().getAttribute("lastURL");
		
		if (lastCommandName == null) {
			lastCommandName = "Controller?commandToController=" + "GO_TO_MAIN_PAGE"; // write to log!!!!!
		} else {
			String path = "Controller?commandToController=" + lastCommandName;
			response.sendRedirect(path);
		}
	}
}
