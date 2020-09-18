package com.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.dao.DAOFactory;

@WebListener
public class DaoFactoryInitializer implements ServletContextListener {

	private DAOFactory daofactory;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		servletContext.log("## App launch ##");
		
		//##FILE_PROPERTIES Connection way##
		this.daofactory = DAOFactory.getInstance();
		//##END process of FILE_PROPERTIES Connection way##
		
		System.out.println("DAOFactory is instantiated!");
		servletContext.setAttribute("daofactory", daofactory);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}
