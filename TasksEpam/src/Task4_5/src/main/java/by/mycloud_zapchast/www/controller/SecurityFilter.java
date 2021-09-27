package by.mycloud_zapchast.www.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.mycloud_zapchast.www.entity.User;
import by.mycloud_zapchast.www.entity.UserRole;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SecurityFilter implements Filter {
	private static final Set<CommandName> withoutAuthorizationCommands=new HashSet<CommandName>();
	private static final Map<CommandName, Set<UserRole>> allowedRoles=new HashMap<>();
//	private static final Map<CommandName, Set<UserRole>> noneAllowedRoles=new HashMap<>();
	private static final String COMMAND_TO_CONTROLLER = "commandToController";
	private static final Logger LOGGER=LogManager.getLogger();
	static {
		Set<UserRole> adminSet=new HashSet<>();
		adminSet.add(UserRole.valueOf("ADMIN"));
		Set<UserRole> employerSet=new HashSet<>();
		employerSet.add(UserRole.valueOf("EMPLOYER"));
		employerSet.addAll(adminSet);
		Set<UserRole> supplierSet=new HashSet<>();
		supplierSet.add(UserRole.valueOf("SUPPLIER"));
		supplierSet.addAll(employerSet);
		
		Set<UserRole> masterSet=new HashSet<>();
		masterSet.add(UserRole.valueOf("MASTER"));
		supplierSet.addAll(supplierSet);
		Set<UserRole> userSet=new HashSet<>();
		userSet.add(UserRole.valueOf("USER"));
		supplierSet.addAll(masterSet);
		
		allowedRoles.put(CommandName.GO_TO_STANDART_PRE_SEARCH, userSet);
		allowedRoles.put(CommandName.ERROR_PAGE, userSet);
		allowedRoles.put(CommandName.STANDART_SEARCH, userSet);
		allowedRoles.put(CommandName.GO_TO_DOCUMENTATION, userSet);
		allowedRoles.put(CommandName.GO_TO_APPLICATION_PRE_SEARCH, userSet);
		allowedRoles.put(CommandName.APPLICATION_SEARCH, userSet);
		allowedRoles.put(CommandName.SIGN_OUT, userSet);
		withoutAuthorizationCommands.add(CommandName.GO_TO_STANDART_PRE_SEARCH);
		withoutAuthorizationCommands.add(CommandName.ERROR_PAGE);
		withoutAuthorizationCommands.add(CommandName.STANDART_SEARCH);
		withoutAuthorizationCommands.add(CommandName.GO_TO_DOCUMENTATION);
		withoutAuthorizationCommands.add(CommandName.SIGN_OUT);
		withoutAuthorizationCommands.add(CommandName.AUTHORIZATION);
		withoutAuthorizationCommands.add(CommandName.REGISTRATION_NEW_USER);
		withoutAuthorizationCommands.add(CommandName.GO_TO_REGISTRATION_2_PAGE);
		withoutAuthorizationCommands.add(CommandName.GO_TO_REGISTRATION_1_PAGE);
		withoutAuthorizationCommands.add(CommandName.REGISTRATION_NEW_USER);
		withoutAuthorizationCommands.add(CommandName.GO_TO_AUTHORIZATION_PAGE);
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = "/WEB-INF/jsp/eror.jsp";
		HttpServletRequest req= (HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		HttpSession session=req.getSession(false);
		String reqCommandName = request.getParameter(COMMAND_TO_CONTROLLER);
		UserRole userRole=null;
		User user=null;
		String message=null;
		Set<UserRole> allowedRolesForThisCommand=null;
		
		// Command is not valid
		if (reqCommandName == null) {
			path= "/WEB-INF/jsp/error.jsp";
			LOGGER.warn("SecurityFilter:  request command Name is null");
			RequestDispatcher requestDispatcher=request.getRequestDispatcher(path);
			requestDispatcher.forward(req, resp);
			return;
		}

		CommandName commandName;
		try {
			commandName = CommandName.valueOf(reqCommandName.toUpperCase());
		} catch (IllegalArgumentException e) { // logging
			path=path = "/WEB-INF/jsp/error.jsp";
			LOGGER.warn("SecurityFilter:  request command Name is not valid");
			RequestDispatcher requestDispatcher=request.getRequestDispatcher(path);
			requestDispatcher.forward(req, resp);
			return;
		}
		System.out.println(session);
		
		
		//User is not authorized
		if( session==null || session.getAttribute("user_session")==null) {
			if(withoutAuthorizationCommands.contains(commandName)) {
				chain.doFilter(req, resp);
				return;
			}
			else {message="У вас нет прав для перехода на эту страницу";
			LOGGER.warn("User went with Command "+commandName+" without rights. User: "+user);
			path= "/WEB-INF/jsp/error.jsp";
			req.setAttribute("user_message", message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher(path);
			requestDispatcher.forward(req, resp);
			return;}
			
		}else {
			try {
			user=(User)session.getAttribute("user_session");
			
			userRole=user.getRole();
			}catch (NullPointerException e) {
				message="У вас нет прав для работы в этом аккаунте, пожалуйста обратитесь к тех. поддержке или заведите новый аккаунт";
				LOGGER.warn("SecurityFilter:  There are user without Role. User:" +user);
				e.printStackTrace();
				path = "/WEB-INF/jsp/error.jsp";
				req.setAttribute("user_message", message);
				RequestDispatcher requestDispatcher=request.getRequestDispatcher(path);
				requestDispatcher.forward(req, resp);
				return;
			}
			
			// user is authorized
		allowedRolesForThisCommand=	allowedRoles.get(commandName);
		if(allowedRolesForThisCommand.contains(userRole)) {
			chain.doFilter(req, resp);
			return;
		}
		else {
			message="У вас нет прав для перехода на эту страницу";
			LOGGER.warn("SecurityFilter:  User went with Command "+commandName+" without rights. User: "+user);
			path= "/WEB-INF/jsp/error.jsp";
			req.setAttribute("user_message", message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher(path);
			requestDispatcher.forward(req, resp);
			return;
		}
		}
		
	}
	

}
