package com.ethan.marvelweb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ethan.marvel.usercards.UserCard;
import com.ethan.marvel.usercards.UserCardDAO;

/**
 * Servlet implementation class UserCardList
 */
@WebServlet("/UserCardList")
public class UserCardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        
	    UserCardDAO dao = new UserCardDAO();
		
		HttpSession session = request.getSession();
		
		String tokenId = (String) session.getAttribute("tknid");
		
		List<UserCard> cards = dao.getUserCards(tokenId);
		
		request.setAttribute("cards", cards);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userCardList.jsp");
		dispatcher.forward(request,response);
		
            dao.closeConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.getWriter().print("[ERROR]"+e);
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
