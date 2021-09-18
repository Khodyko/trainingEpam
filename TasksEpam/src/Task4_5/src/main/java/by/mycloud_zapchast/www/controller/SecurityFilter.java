package by.mycloud_zapchast.www.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SecurityFilter implements Filter {
	private static final Map<String, Set<String>> allowedRoles=new HashMap<>();
	//private static final Map<String, Set<String>> noneAllowedRoles=new HashMap<>();
	static {
		Set<String> adminSet=new HashSet<>();
		adminSet.add("ADMIN");
		Set<String> employeeSet=new HashSet<String>();
		
		adminSet.add("EMPLOYEE");
		allowedRoles.put("/main.jsp", adminSet);
		//need finish!!!!!!!!!!!!!!!!!!!!
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req= (HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		HttpSession session=req.getSession(false);
		String context= req.getContextPath();
		String uri = req.getContextPath();
		uri=uri.substring(context.length());
		
//		if(session!=null) {
//			chain.doFilter(req, resp);
//			return;
//		}
		System.out.println("filter has done");
		chain.doFilter(req, resp);
	}
	

}
