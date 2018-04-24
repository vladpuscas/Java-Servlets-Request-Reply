package servlet;

import dao.FlightDao;
import entities.Flight;
import entities.User;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vlad on 24-Oct-17.
 */
@WebServlet(name = "user")
public class UserServlet extends HttpServlet {
    private FlightDao flightDao;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");
        response.setContentType("text/html");
        response.getWriter().println("Hello " + user.getUsername());
        List<Flight> flights = flightDao.getAllFlights();

        response.getWriter().println("<ul>");
        for (Flight f:flights) {
            response.getWriter().print("<li>" + f.toString() + "</li>");
            response.getWriter().print("<form action=\"getLocalTimes\" method=\"post\">\n" +
                    "        <input type=\"hidden\" value=\"" + f.getArrivalCityId()  +"\" name=\"arrivalId\">\n" +
                    "        <input type=\"hidden\" value=\"" + f.getDepartureCityId() + "\" name=\"departureId\">\n" +
                    "        <input type=\"submit\" value=\"Get Local Times\"/>\n" +
                    "    </form>");
        }
        response.getWriter().println("</ul>");
    }

    public void init() {
        flightDao = new FlightDao(new Configuration().configure().buildSessionFactory());
    }
}
