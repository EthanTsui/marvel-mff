package com.ethan.marvelweb;

import com.ethan.marvel.usercards.UserCard;
import com.ethan.marvel.usercards.UserCardDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ethan Yin-Hao Tsui on 2018/4/11.
 */
@WebServlet("/UserModifyCard")
public class UserModifyCard extends HttpServlet {



    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            UserCardDAO dao = new UserCardDAO();
            UserCard card = dao.getUserCard(request.getParameter("cuid"));
            request.setAttribute("usercard", card);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userAddNewCard.jsp?cardid=" + card.getCardId()+"&lang="+request.getParameter("lang"));
            dispatcher.forward(request,response);

        }
        catch (Exception err) {
            err.printStackTrace();

        }

    }
}
