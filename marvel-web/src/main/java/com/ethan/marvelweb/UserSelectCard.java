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
import java.io.IOException;

/**
 * Created by Ethan Yin-Hao Tsui on 2018/4/16.
 */
@WebServlet("/UserSelectCard")
public class UserSelectCard extends HttpServlet {
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCardDAO dao = null;
        try {
            dao = new UserCardDAO();

            UserCollection collection = dao.getUserCollection(request.getParameter("collectionuid"));

            UserCard card = dao.getUserCard(request.getParameter("cuid"));

            boolean found = false;

            for(int i=1;i<=5;i++) {
                UserCard uCard = collection.getSlotCard(i);
                if(uCard==null) {
                    continue;
                }
                else if(uCard.getCardId()==card.getCardId() && i!=Integer.parseInt(request.getParameter("slotid"))) {
                    found=true;
                    break;
                }
            }

            if(found) {

                response.sendRedirect("./UserCardCollection?lang=" + request.getParameter("lang")+"&error=conflict&collectionuid=" + request.getParameter("collectionuid")+"#col-"+collection.getCollectionUId());

            }
            else {
                UserCard oldCard = collection.getSlotCard(Integer.parseInt(request.getParameter("slotid")));

                collection.setSlotCard(Integer.parseInt(request.getParameter("slotid")), card);

                collection.reCalculateSkills();

                dao.upsertUserCollection(collection);

                if (oldCard != null) {
                    response.sendRedirect("./UserCardCollection?lang=" + request.getParameter("lang") + "&slotid=" + request.getParameter("slotid") + "&collectionuid=" + request.getParameter("collectionuid") + "&cuid=" + oldCard.getCardUId() + "#col-" + collection.getCollectionUId());
                } else {
                    response.sendRedirect("./UserCardCollection?lang=" + request.getParameter("lang") + "#col-" + collection.getCollectionUId());
                }
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
