package com.ethan.marvelweb;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ethan.marvel.Helper;
import com.ethan.marvel.HeroDBFactory;
import com.ethan.marvel.HeroTeam;
import com.ethan.marvel.sorter.AttackSorter;
import com.ethan.marvel.sorter.SuggestionSorter;

/**
 * Servlet implementation class QueryTeam
 */
@WebServlet("/QueryTeam")
public class QueryTeam extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryTeam() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("herolist") != null && request.getParameter("herolist").length() > 0) {

            // BufferedWriter bw = new
            // BufferedWriter(FileOutputStream("/data/marvel/logs/"));
            
            
            int leaderHero = 0;
            if(request.getParameter("leaderhero")!=null) {
                try {
                    leaderHero = Integer.parseInt(request.getParameter("leaderhero"));
                }
                catch(Exception err) {
                    err.printStackTrace();
                }
            }

            String heroliststr = request.getParameter("herolist");
            String[] ids = heroliststr.split("_");

            List<Integer> herolist = new ArrayList<Integer>();
            for (String id : ids) {
                herolist.add(Integer.parseInt(id));
            }

            Helper helper = new Helper();

//            if (request.getParameter("lang") != null) {
//                helper.setLanguage(request.getParameter("lang"));
//            }

            HeroTeam heroteam = null;

            if (request.getParameter("heroteam") != null && request.getParameter("heroteam").length() > 0) {
                String[] sh = request.getParameter("heroteam").trim().split("_");
                heroteam = new HeroTeam();
                for (String s : sh) {
                    heroteam.addHero(helper.getHero(Integer.parseInt(s)));
                }
            }

            List<HeroTeam> team = helper.list3(herolist, heroteam, leaderHero);
            
            Comparator<HeroTeam> sorter = null;
            if(request.getParameter("sorter")!=null && request.getParameter("sorter").equals("ns")) {
                sorter = new SuggestionSorter();
            }
            else {
                sorter=new AttackSorter();
            }
            
            Collections.sort(team,sorter);
            
            
            request.setAttribute("heroteam", team);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/querymarvel.jsp");
        rd.forward(request, response);

    }

    /**
     * @param string
     * @return
     */
    private Writer FileOutputStream(String string) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doGet(request, response);
    }

}
