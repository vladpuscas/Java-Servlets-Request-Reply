package servlet;

import dao.FlightDao;
import entities.Flight;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vlad on 29-Oct-17.
 */
@WebServlet(name = "deleteFlight")
public class DeleteFlightServlet extends HttpServlet {
    private FlightDao flightDao;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        flightDao.deleteFlight(Integer.parseInt(request.getParameter("flightId")));
        response.sendRedirect("deleteFlight");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            response.getWriter().println("<form action=\"deleteFlight\" method=\"post\">\n" +
                    "        Flight ID:<br>\n" +
                    "        <input type=\"number\" name=\"flightId\"/><br>\n" +
                    "        <input type=\"submit\"/>\n" +
                    "    </form>");
        }
    }

    public void init() {
        flightDao = new FlightDao(new Configuration().configure().buildSessionFactory());
    }

}
