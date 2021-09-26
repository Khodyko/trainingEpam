package by.mycloud_zapchast.www.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.mycloud_zapchast.www.entity.AppSearchItem;
import by.mycloud_zapchast.www.entity.StandartSearchItem;
import by.mycloud_zapchast.www.entity.User;
import by.mycloud_zapchast.www.service.ItemService;
import by.mycloud_zapchast.www.service.ServiceException;
import by.mycloud_zapchast.www.service.ServiceProvider;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PageCounterFilter implements Filter {
	private static final String COMMAND_TO_CONTROLLER = "commandToController";
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final ItemService ITEM_SERVICE = PROVIDER.getItemService();
	private static final Logger LOGGER=LogManager.getLogger();
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String reqCommandName = request.getParameter(COMMAND_TO_CONTROLLER);
		
		switch (reqCommandName) {
		case ("STANDART_SEARCH"): {
			String path = "/WEB-INF/jsp/eror.jsp";
			StandartSearchItem itemFromUI = new StandartSearchItem();
			String message = null;
			Integer currentPageNumber = 1;
			Integer pagesMaxNum = 1;
			String currentPageFromUI = request.getParameter("currentPage");
			currentPageNumber = currentPageFromUI == null ? 1 : Integer.parseInt(currentPageFromUI);
			String name = request.getParameter("itemName");
			String nn = request.getParameter("nn");
			String nnSap = request.getParameter("nnSap");
			itemFromUI.setName(name);
			itemFromUI.setNn(nn);
			itemFromUI.setNnSap(nnSap);
			System.out.println("filter: itemfromui : "+itemFromUI);
			try {
				pagesMaxNum = ITEM_SERVICE.getStandartSearchItemMaxNumber(itemFromUI);
				pagesMaxNum = (pagesMaxNum % 30) > 0 ? pagesMaxNum / 30 + 1 : pagesMaxNum / 30;
			} catch (ServiceException e) {
				path = "/WEB-INF/jsp/error.jsp";
				message = e.getMessage();
				LOGGER.warn("PageCounterFilter "+e.getStackTrace());
				request.setAttribute("user_message", message);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
				requestDispatcher.forward(request, response);
				return;
			}

			request.setAttribute("currentPage", currentPageNumber);
			request.setAttribute("pagesMaxNum", pagesMaxNum);
			chain.doFilter(request, response);
			return;
		}
		
		case ("APPLICATION_SEARCH"): {
			String path = "/WEB-INF/jsp/eror.jsp";
			AppSearchItem itemFromUI = new AppSearchItem();
			String message = null;
			Integer currentPageNumber = 1;
			Integer pagesMaxNum = 1;
			HttpSession session = ((HttpServletRequest) request).getSession(true);
			User user=(User) session.getAttribute("user_session");
			String currentPageFromUI = request.getParameter("currentPage");
			currentPageNumber = currentPageFromUI == null ? 1 : Integer.parseInt(currentPageFromUI);
			String name = request.getParameter("itemName");
			String nn = request.getParameter("nn");
			String nnSap = request.getParameter("nnSap");
			String year = request.getParameter("year");
			itemFromUI.setName(name);
			itemFromUI.setNn(nn);
			itemFromUI.setNnSap(nnSap);
			itemFromUI.setYear(year);
			System.out.println("filter: itemfromui : "+itemFromUI);
			try {
				pagesMaxNum = ITEM_SERVICE.getAppSearchItemMaxNumber(itemFromUI, user);
				pagesMaxNum = (pagesMaxNum % 30) > 0 ? pagesMaxNum / 30 + 1 : pagesMaxNum / 30;
			} catch (ServiceException e) {
				path = "/WEB-INF/jsp/error.jsp";
				message = e.getMessage();
				LOGGER.warn("PageCounterFilter "+e.getStackTrace());
				request.setAttribute("user_message", message);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
				requestDispatcher.forward(request, response);
				return;
			}

			request.setAttribute("currentPage", currentPageNumber);
			request.setAttribute("pagesMaxNum", pagesMaxNum);
			chain.doFilter(request, response);
			return;
		}
		
		default:chain.doFilter(request, response);
		}
		

	}

}
