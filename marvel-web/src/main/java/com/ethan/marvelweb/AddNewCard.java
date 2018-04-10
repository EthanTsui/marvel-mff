package com.ethan.marvelweb;

import com.ethan.marvel.usercards.UserCard;
import com.ethan.marvel.usercards.UserCardDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ethan Yin-Hao Tsui on 2018/4/10.
 */
@WebServlet("/AddNewCard")
public class AddNewCard extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewCard() {
        super();

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
