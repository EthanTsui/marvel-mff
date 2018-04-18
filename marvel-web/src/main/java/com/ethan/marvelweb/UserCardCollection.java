package com.ethan.marvelweb;

import com.ethan.marvel.usercards.UserCardDAO;
import com.ethan.marvel.usercards.UserCollection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Ethan Yin-Hao Tsui on 2018/4/11.
 */
@WebServlet("/UserCardCollection")
public class UserCardCollection extends HttpServlet {

    public UserCardCollection() {
        super();
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCardDAO dao = null;

        try {
            dao = new UserCardDAO();
            List<UserCollection> collections = dao.getAllUserCollection((String)request.getSession().getAttribute("tknid"));
            request.setAttribute("collections", collections);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userCardCollection.jsp?lang="+request.getParameter("lang"));
            dispatcher.forward(request,response);

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
}
