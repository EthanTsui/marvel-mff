package com.ethan.marvelweb;

import com.ethan.marvel.usercards.UserCard;
import com.ethan.marvel.usercards.UserCardDAO;
import com.ethan.marvel.usercards.UserCollection;

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
 * Created by Ethan Yin-Hao Tsui on 2018/4/19.
 */
@WebServlet("/UserCollectionTop")
public class UserCollectionTop extends HttpServlet {
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCardDAO dao = null;

        try {

            dao = new UserCardDAO();

            int top=20;

            String sort = "sk_9_26";

            float upperBound = 999f;

            if(request.getParameter("topn")!=null) {
                try {
                    top = Integer.parseInt(request.getParameter("topn"));
                }
                catch (Exception ignore) {

                }
            }

            if(request.getParameter("sortby")!=null) {
                sort = request.getParameter("sortby");
            }

            if(request.getParameter("upper")!=null) {
                try {
                    upperBound = Float.parseFloat(request.getParameter("upper"));
                }
                catch (Exception ignore) {

                }

                if(upperBound<=0f) {
                    upperBound=0f;
                }
            }


            List<UserCollection> collections = dao.getTop(top, sort, upperBound);

            request.setAttribute("collections", collections);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userCollectionTop.jsp");
            dispatcher.forward(request,response);

            dao.closeConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
