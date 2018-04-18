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
@WebServlet("/UserAddNewCard")
public class UserAddNewCard extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddNewCard() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCardDAO dao = null;
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

            for(int i=1;i<=6;i++) {
                card.setOptions(i, request.getParameter("opt"+i));
            }

            dao = new UserCardDAO();

            dao.upsertUserCard(card);


            if(request.getParameter("slotid")!=null && request.getParameter("collectionuid")!=null) {
                response.sendRedirect("./UserSelectCard?lang=" + request.getParameter("lang")+"&cuid="+card.getCardUId()+"&slotid="+request.getParameter("slotid")+"&collectionuid="+request.getParameter("collectionuid"));

            }
            else {
                response.sendRedirect("./UserCardList?lang=" + request.getParameter("lang"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().print("[ERROR]"+e);
        }
        finally {
            try {
                if(dao!=null) {
                    dao.closeConnection();
                }
            }
            catch (Exception ignore) { }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
