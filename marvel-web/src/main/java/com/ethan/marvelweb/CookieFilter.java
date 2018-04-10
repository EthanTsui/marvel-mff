package com.ethan.marvelweb;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet Filter implementation class CookieFilter
 */
@WebFilter(filterName="cookieFilter", urlPatterns={"/*"})
public class CookieFilter implements Filter {

    private static final char[] CHARS62 = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9' };



    /**
     * Default constructor.
     */
    public CookieFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }


    private static final Random RANDOM = new Random();
    public static String generateRandomIds(int numberOfChar) {
        char[] output = new char[numberOfChar];
        for (int i = 0; i < numberOfChar; i++) {
            output[i] = CHARS62[RANDOM.nextInt(62)];
        }
        return new String(output);
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest hreq = (HttpServletRequest) request;
            HttpServletResponse hres = (HttpServletResponse) response;
            HttpSession session = ((HttpServletRequest) request).getSession();

            if (session.getAttribute("tknid") == null) {

                Cookie[] cookies = hreq.getCookies();
                boolean found = false;

                if (cookies != null) {
                    for (Cookie c : cookies) {
                        if (c.getName() != null && c.getName().equals("tknid")) {
                            found = true;

                            session.setAttribute("tknid", c.getValue());
                            
                            break;
                        }
                    }
                }
                if (!found) {
                    Cookie cookie = new Cookie("tknid", generateRandomIds(20));
                    cookie.setDomain(".ethanjojo.com");
                    cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
                    cookie.setPath("/");
                    hres.addCookie(cookie);

                    session.setAttribute("tknid", cookie.getValue());

                }
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
