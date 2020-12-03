package com.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

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
	
	private UserDaoImpl userDao;

    public MainController() {
    	
    }

    @Override
	public void init() throws ServletException {
		super.init();
    	LOG.log(Level.INFO, "Test of logger on init() method");		
		/*//OLD Way to get UserDaoImpl userDao
		DAOFactory daofactory = (DAOFactory) getServletContext().getAttribute("daofactory");
    	UserDaoImpl userDao = (UserDaoImpl) daofactory.getUserDAO();*/
		
    	this.userDao = (UserDaoImpl) ((DAOFactory) getServletContext().getAttribute("daofactory")).getUserDAO();
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
				else if((login != null && login.trim().length() > 0) && (code != null && code.trim().length() > 0)) {
					User user = this.userDao.find(login);

					//If database returned an User, go on next process.
					if(user != null) {
						boolean isCorrectPwd = this.userDao.isPasswordMatchHashcode(code, user.getPassword());
						
						if(isCorrectPwd) {
							LOG.log(Level.INFO, "New User Session start");
							HttpSession session = request.getSession();
							session.setAttribute("login", login);
							session.setAttribute("isConnected", true);
							pathname = "./user.jsp";
						}
						else {
							request.setAttribute("userWarningMsg", "Error on User login or password ! You should retry. ");
						}
					}
					else {
						request.setAttribute("userWarningMsg", "User not found ! (Unknown Member)");
					}

				}
				else {
					System.out.println("404 Mock page View");
					pathname = "./404.jsp";
				}
				break;
			case "signUp":
				System.out.println("Registration process");
				System.out.println("userProfile: " + request.getParameter("userProfile"));
				
				//Stock 2 more inputs from SignUp form ("name", "userProfile")
				String name = request.getParameter("name");
				String userProfile = request.getParameter("userProfile");
				
				//Check If all required inputs are filled out
				if((login != null && login.trim().length() > 0) && (code != null && code.trim().length() > 0) 
						&& (name != null && name.trim().length() > 0) && (userProfile != null && userProfile.trim().length() > 0)) {
					User newMember = this.userDao.find(login);
					System.out.println("newMember res : " + newMember);
					
					//If login already exist, warn the newMember
					if(newMember != null) {						
						request.setAttribute("userWarningMsg", "\"" + login + "\" login is already used. You should try another one.");
						pathname = "./signUp.jsp";
					}
					else {
						//Email verification for various possibilities (starts with 1 char before '@', never starts or ends by char [.-_](even before '@'), etc.)
						//REGEX: #^(([a-z0-9])+|([a-z0-9][a-z0-9._-]*[a-z0-9]))@(([a-z0-9])|([a-z0-9][a-z0-9._-])){2,}[a-z0-9]\.[a-z]{2,4}$#
						String regex = "^(([a-z0-9])+|([a-z0-9][a-z0-9._-]*[a-z0-9]))@(([a-z0-9])|([a-z0-9][a-z0-9._-])){2,}[a-z0-9]\\.[a-z]{2,4}$";
						System.out.println("Regex res: " + Pattern.matches(regex, login));
						
						//Not a proper match
						if(!Pattern.matches(regex, login)) {
							request.setAttribute("userWarningMsg", "Error on login \"<strong>" + login + "</strong>\". Please enter a valid email address.<br />For example, driver@gmail.com");
							pathname = "./signUp.jsp";
						}
						else {//Match well with email regex
							if(code.trim().length() < 5) {
								request.setAttribute("userWarningMsg", "Password must include <strong>at least 5 characters.</strong> Please enter a valid password.");
								pathname = "./signUp.jsp";
							}
							else {
								System.out.println("Registration db process !");

								newMember = new User();
								newMember.setName(name);
								newMember.setProfileType(userProfile);
								newMember.setEmail(login);
								newMember.setPassword(this.userDao.makeHashcode(code));
								
								this.userDao.create(newMember);
								System.out.println(newMember);
							}
						}
					}
				}
				else {
					request.setAttribute("userWarningMsg", "All fields are required ! Please fill out.");
					pathname = "./signUp.jsp";
				}
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
