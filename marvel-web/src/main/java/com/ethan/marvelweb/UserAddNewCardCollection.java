package com.ethan.marvelweb;

import com.ethan.marvel.usercards.UserCard;
import com.ethan.marvel.usercards.UserCardDAO;
import com.ethan.marvel.usercards.UserCollection;
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
 * Created by Ethan Yin-Hao Tsui on 2018/4/17.
 */
@WebServlet("/UserAddNewCardCollection")
public class UserAddNewCardCollection extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCardDAO dao = null;

        try {

            dao = new UserCardDAO();

            HttpSession session = request.getSession();

            String tokenId = (String) session.getAttribute("tknid");


            UserCollection collection = new UserCollection();

            collection.setTokenId(tokenId);
            collection.setCollectionUId(RandomIdGenerator.generateRandomIds(20));

            dao.upsertUserCollection(collection);

            response.sendRedirect("./UserCardCollection?lang="+request.getParameter("lang"));

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
