package by.mycloud_zapchast.www.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.mycloud_zapchast.www.dao.connectionpool.ConnectionPoolException;
import by.mycloud_zapchast.www.dao.connectionpool.ItemConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * 
 * Close PoolConnection, when servlet is destroing
 * 
 * @author Vitamin_XO
 *
 */

@WebListener
public class CloseListener implements ServletContextListener {
	private static final Logger LOGGER = LogManager.getLogger();
	ItemConnectionPool connectionPool;

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		connectionPool = ItemConnectionPool.getInstance();
		try {
			connectionPool.closeItemConnectionPool();
			LOGGER.warn("ZAKRIVAEM POOL");
		} catch (ConnectionPoolException e) {
			LOGGER.warn("ConnectionPool is clossing with error");
		}
		;

	}
}
