package com.ethan.marvelweb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ethan.marvel.utils.RandomIdGenerator;

/**
 * Servlet implementation class UserLoadProfile
 */
@WebServlet("/UserLoadProfile")
public class UserLoadProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoadProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    if(request.getParameter("tokenid")!=null) {
	        Cookie cookie = new Cookie("tknid", request.getParameter("tokenid"));
            cookie.setDomain(".ethanjojo.com");
            cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
            cookie.setPath("/");
            
            response.addCookie(cookie);
            
            request.getSession().setAttribute("tknid", request.getParameter("tokenid"));

	    }

	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userLoadProfile.jsp?lang="+request.getParameter("lang"));
        dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
