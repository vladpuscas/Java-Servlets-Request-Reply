package servlet;

import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Vlad on 25-Oct-17.
 */
@WebServlet(name = "admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        Cookie cookies[] = request.getCookies();
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (c.getName().equalsIgnoreCase("role")) {
                cookie = c;
                break;
            }
        }

        if (cookie.getValue().equalsIgnoreCase("USER")) {
            response.sendError(403);
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<ul>\n" +
                    "    <a href=\"addFlight\">Add a new Flight</a>\n" +
                    "    <a href=\"searchFlight\">Search Flight</a>\n" +
                    "    <a href=\"deleteFlight\">Delete Flight</a>\n" +
                    "    <a href=\"updateFlight\">Update Flight</a>\n" +
                    "    <a href=\"addCity\">Add a new City</a>\n" +
                    "</ul>");
        }
    }
}
