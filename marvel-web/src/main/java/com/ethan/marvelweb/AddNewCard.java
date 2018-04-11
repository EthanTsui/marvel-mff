package com.ethan.marvelweb;

import com.ethan.marvel.usercards.UserCard;
import com.ethan.marvel.usercards.UserCardDAO;
import com.ethan.marvel.utils.RandomIdGenerator;

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

            UserCard card = new UserCard();


            card.setTokenId((String) request.getSession().getAttribute("tknid"));

            if(request.getParameter("carduid")!=null) {
                card.setCardUId(request.getParameter("carduid"));
            }
            else {
                String cardUId = RandomIdGenerator.generateRandomIds(20);
                card.setCardUId(cardUId);
            }
            card.setCardId(Integer.parseInt(request.getParameter("cardid")));
            card.setLevel(Integer.parseInt(request.getParameter("level")));
            card.setOption1(request.getParameter("opt1"));
            card.setOption2(request.getParameter("opt2"));
            card.setOption3(request.getParameter("opt3"));
            card.setOption4(request.getParameter("opt4"));
            card.setOption5(request.getParameter("opt5"));
            card.setOption6(request.getParameter("opt6"));



            UserCardDAO dao = new UserCardDAO();

            dao.upsertUserCard(card);

            dao.closeConnection();

            response.sendRedirect("./UserCardList?lang="+request.getParameter("lang"));


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
