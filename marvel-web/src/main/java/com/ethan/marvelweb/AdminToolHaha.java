package com.ethan.marvelweb;

import com.ethan.marvel.usercards.UserCardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ethan Yin-Hao Tsui on 2018/4/19.
 */
@WebServlet("/AdminToolHaha")
public class AdminToolHaha extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCardDAO dao = null;

        try {
            dao = new UserCardDAO();

            if("clear-card".equals(request.getParameter("command"))) {
                dao.removeRepeatCard();

            }

        }
        catch (Exception err) {
            err.printStackTrace();

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
