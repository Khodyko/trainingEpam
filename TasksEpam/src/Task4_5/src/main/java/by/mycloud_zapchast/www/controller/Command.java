package by.mycloud_zapchast.www.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * 
 * @author Vitamin_XO
 *
 */
public interface Command {
	
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
}
