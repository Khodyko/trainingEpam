package by.hodyko.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.hodyko.www.bean.News;
import by.hodyko.www.controller.Command;
import by.hodyko.www.service.NewsService;
import by.hodyko.www.service.ServiceException;
import by.hodyko.www.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToMainPage implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final NewsService NEWS_SERVICE = PROVIDER.getNewService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/main.jsp";
		String lastCommandName = "GO_TO_MAIN_PAGE";
		Integer currentPageNumber=1;
		Logger logger=LogManager.getLogger();
		
		Integer pagesMaxNum=1;
		
		HttpSession session = request.getSession(true);
		

		try {
			pagesMaxNum = NEWS_SERVICE.getNewsMaxNumber();
			pagesMaxNum=(pagesMaxNum%5)>0?pagesMaxNum/5+1:pagesMaxNum/5;
		} catch (ServiceException e) {
			path = "/WEB-INF/jsp/unknownPage.jsp";
			logger.warn("command " +lastCommandName+" newsNumber is not available"+" "+e.getMessage());
			lastCommandName = "UNKNOWN_COMMAND";
			session.setAttribute("lastURL", lastCommandName); // for redirect in localization
			response.sendRedirect("Controller?commandToController=" + path);
			return;
		}
		
			
			currentPageNumber = pageNumberConverter( request.getParameter("currentPage"));
			
		
			if(pagesMaxNum<(currentPageNumber)) {
				currentPageNumber=1;
			}

			request.setAttribute("currentPage", currentPageNumber);
			request.setAttribute("pagesMaxNum", pagesMaxNum);
			

		try {
			List<News> newses = NEWS_SERVICE.getNewsList(currentPageNumber);
			request.setAttribute("newses", newses);
		} catch (ServiceException e) {
			logger.warn("command " +lastCommandName+" News is not available"+" "+e.getMessage());
			path = "/WEB-INF/jsp/unknownPage.jsp";
			lastCommandName = "UNKNOWN_COMMAND";
			session.setAttribute("lastURL", lastCommandName); // for redirect in localization
			response.sendRedirect("Controller?commandToController=" + path);
			return;
		}
		lastCommandName="GO_TO_MAIN_PAGE&currentPage="+currentPageNumber;
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		request.getSession(true).setAttribute("lastURL", lastCommandName); // for redirect in localization
		requestDispatcher.forward(request, response);
	}

	private Integer pageNumberConverter(String currentPageNumber) {

		if (currentPageNumber == null || currentPageNumber.equals("")) {
			currentPageNumber = "1";
		}
		
		
		return Integer.parseInt(currentPageNumber);
	}

}
