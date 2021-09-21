package by.hodyko.www.controller.controllerCommandMethods;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.hodyko.www.bean.User;
import by.hodyko.www.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToAddNewsPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String userRole = (((User) request.getSession(false).getAttribute("user")).getRole()).toString();
		String path = "/WEB-INF/jsp/addNewsPage.jsp";
		String lastCommandName = "ADD_NEWS_PAGE";
		Logger logger=LogManager.getLogger();
		if (userRole != "ADMIN") {
			path = "/WEB-INF/jsp/unknownPage.jsp";
			logger.warn("command " + lastCommandName + " user has gone to add-news-pag without permission");
			lastCommandName = "UNKNOWN_COMMAND";
			session.setAttribute("lastURL", lastCommandName); // for redirect in localization
			response.sendRedirect("Controller?commandToController=" + path);
			return;
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		session.setAttribute("lastURL", lastCommandName); // for redirect in localization
		requestDispatcher.forward(request, response);
	}
}
