package com.ethan.marvelweb;

import com.ethan.marvel.usercards.Combination2;
import com.ethan.marvel.usercards.UserCard;
import com.ethan.marvel.usercards.UserCardDAO;
import com.ethan.marvel.usercards.UserCardOptimizer;

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
 * Created by Ethan Yin-Hao Tsui on 2018/4/20.
 */
@WebServlet("/UserCardSuggest")
public class UserCardSuggest extends HttpServlet {
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCardDAO dao = null;

        try {

            dao = new UserCardDAO();

            HttpSession session = request.getSession();

            String tokenId = (String) session.getAttribute("tknid");

            List<UserCard> cards = dao.getUserCards(tokenId);

            UserCardOptimizer optimizer = new UserCardOptimizer(cards);

            if(request.getParameter("sortby")!=null) {
                optimizer.setSkillId(request.getParameter("sortby"));
            }

            optimizer.run();

            request.setAttribute("collections", optimizer.getCollections());

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userCollectionSuggest.jsp");
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
}
