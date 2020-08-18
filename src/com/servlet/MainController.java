package com.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DAOFactory;
import com.dao.UserDaoImpl;
import com.model.User;

/**
 * Servlet implementation class MainController
 */
@WebServlet(urlPatterns = {"/signIn", "/signUp"}, loadOnStartup = 1)
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(Class.class.getName());

    public MainController() {
    	
    }

    @Override
	public void init() throws ServletException {
		super.init();
    	LOG.log(Level.INFO, "Test of logger on init() method");
		
		System.out.println("\n##FILE_PROPERTIES Connection way##");
		
		DAOFactory daofactory = (DAOFactory) getServletContext().getAttribute("daofactory");
    	UserDaoImpl userDao = (UserDaoImpl) daofactory.getUserDAO();
		
    	System.out.println(userDao.find("dom_t@gmail.com"));
    	User user = userDao.find("dom_t@gmail.com");
    	System.out.println("Password: " +user.getPassword());//"dom01"
    	String plaincode = "dom01";
    	
    	try {
    		MessageDigest md = MessageDigest.getInstance("SHA-256");

    	    md.update(plaincode.getBytes(StandardCharsets.UTF_8));
    	    byte[] digest = md.digest();

    	    String hex = String.format("%064x", new BigInteger(1, digest));
    		
			// // // //
    	    System.out.println("hexint64: " + hex);
		}
    	catch (Exception e) {
			e.printStackTrace();
			System.out.println("[======= See MSG CLEARly =======]\n");
			e.getMessage();
		}

    	System.out.println("\n##END process of FILE_PROPERTIES Connection way##");
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionState = (String) request.getParameter("state"), pathname = "./";
		//Check if user attempt to log off
		boolean disconnected = (sessionState != null && "logOff".equals(sessionState));
		
		//Set the last URI part of request in uriEndPart
		String uriEndPart = getUriLastPortion(request.getRequestURI());
		System.out.println(request.getContextPath());
		System.out.println(request.getRequestURI());
		
		//Check if uriEndPart ends with "signIn" without consider parameter(s) presence
		if("signIn".equals(uriEndPart)) {
			
			if(disconnected) {
				//Closing Session Handler
				LOG.log(Level.INFO, "Ending of Session");
				request.getSession().invalidate();
				pathname = this.getServletContext().getContextPath();				
			}
			else {
				//Declaration of session
				HttpSession session = request.getSession(true);
				
				//Get login's value typed in index.jsp's Form
				String login = (String) session.getAttribute("login");
				System.out.println("id: "+ login);System.out.println("\npathname: "+ pathname);
				
				if(login != null && login.trim().length() > 0) {
					pathname="./user.jsp";
				}
				else {
					System.out.println("Session lost !");
					pathname="./index.jsp";
				}
			}			
		}		
		else {
			//response.getWriter().append("Served at: ").append(request.getContextPath());
			//Redirection to login page after time interval
			pathname="./404.jsp";
			System.out.println("Back to HomePage!");
		}

		if(disconnected) {
			/*response.sendRedirect(this.getServletContext().getContextPath());
			return;*/
			response.sendRedirect(pathname);
		}
		else {
			request.getRequestDispatcher(pathname).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isAdmin = false;
		String pathname = "./";
		String login = (String) request.getParameter("login");
		String code = (String) request.getParameter("pwd");
		System.out.println("login: " + login + " code: " + code);
		
		System.out.println(request.getRequestURI());		
		String uriEndPart = getUriLastPortion(request.getRequestURI());
		
		//Check of requestURI's last part after split("/")
		switch(uriEndPart) {
			case "signIn":
				System.out.println("User Connection process");
				
				if("admin".equals(login) && "root".equals(code)) {
					User[] user = {new User(1, "Dominic Toretto", "Buyer", "dom_t@gmail.com", "dom01"), new User(2, "Brian O'Conner", "Buyer", "oconner@outlook.com", "ocb02"),
							new User(3, "Tej Parker", "Dealer", "tparker@gmail.com", "tej03"), new User(4, "Han Lue", "Dealer", "han_l@protonmail.com", "han123")};
					
					isAdmin = true;
					
					LOG.log(Level.INFO, "Admin Session start");
					HttpSession session = request.getSession();
					session.setAttribute("login", login);
					session.setAttribute("isAdmin", isAdmin);
					session.setAttribute("isConnected", true);
					session.setAttribute("user", user);
					
					pathname = "./admin.jsp";
				}
				//else if(login.equals("dom") && code.equals("dt")) {
				else if((login != null && login.trim().length() > 0) && (code != null && code.trim().length() > 0)) {
					
					LOG.log(Level.INFO, "New User Session start");
					HttpSession session = request.getSession();
					session.setAttribute("login", login);
					session.setAttribute("isConnected", true);
					pathname = "./user.jsp";
				}
				else {
					System.out.println("404 Mock page View");
					pathname = "./404.jsp";
				}
				break;
			case "signUp":
				System.out.println("Registration process");
				break;
			/*default:
				System.out.println("Back to HomePage");
				request.getRequestDispatcher("./404.jsp").forward(request, response);
			*/
		}
		request.getRequestDispatcher(pathname).forward(request, response);
	}
	
	private static String getUriLastPortion(String uri) {
		String[] uriPortions = uri.split("/");
		return uriPortions[uriPortions.length-1];
	}
}
