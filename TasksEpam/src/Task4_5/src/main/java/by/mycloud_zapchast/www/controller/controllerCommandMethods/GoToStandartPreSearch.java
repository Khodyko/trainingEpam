package by.mycloud_zapchast.www.controller.controllerCommandMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.mycloud_zapchast.www.controller.Command;
import by.mycloud_zapchast.www.service.ItemService;
import by.mycloud_zapchast.www.service.ServiceProvider;
import by.mycloud_zapchast.www.service.impl.MailSenderImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToStandartPreSearch implements Command {

/**
 * Plug before StandartSearch
 * 
 * @author Vitamin_XO
 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/jsp/standart_pre_search.jsp";		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

}
