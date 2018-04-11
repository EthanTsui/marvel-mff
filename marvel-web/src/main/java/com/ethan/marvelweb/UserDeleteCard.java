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
@WebServlet("/UserDeleteCard")
public class UserDeleteCard extends HttpServlet {
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            UserCardDAO dao = new UserCardDAO();

            dao.deleteUserCard(request.getParameter("cuid"));

            response.sendRedirect("./UserCardList?lang="+request.getParameter("lang"));

        }
        catch (Exception err) {
            err.printStackTrace();

        }

    }
}
