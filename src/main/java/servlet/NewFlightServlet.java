package servlet;

import dao.FlightDao;
import entities.Flight;
import entities.User;
import org.hibernate.cfg.Configuration;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Vlad on 29-Oct-17.
 */
@WebServlet(name = "addFlight")
public class NewFlightServlet extends HttpServlet {
    private FlightDao flightDao;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Flight flight = new Flight();
        flight.setAirplaneType(request.getParameter("AirplaneType"));
        flight.setArrivalCityId(Integer.parseInt(request.getParameter("ArrivalCityId")));
        flight.setDepartureCityId(Integer.parseInt(request.getParameter("DepartureCityId")));
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        Date date = DateTime.parse(request.getParameter("ArrivalTime")).toDate();
        flight.setArrivalDate(date);
        date = DateTime.parse(request.getParameter("DepartureTime")).toDate();
        flight.setDepartureDate(date);
        flightDao.addFlight(flight);
        response.sendRedirect("addFlight");
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
            response.getWriter().println("<form action=\"addFlight\" method=\"post\">\n" +
                    "        Airplane type:<br>\n" +
                    "        <input type=\"text\" name=\"AirplaneType\"/> <br>\n" +
                    "        Arrival City ID:<br>\n" +
                    "        <input type=\"text\" name=\"ArrivalCityId\"/><br>\n" +
                    "        Departure City ID:<br>\n" +
                    "        <input type=\"text\" name=\"DepartureCityId\"/> <br>\n" +
                    "        Departure time:<br>\n" +
                    "        <input type=\"datetime-local\" name=\"DepartureTime\"/> <br>\n" +
                    "        Arrival time: <br>\n" +
                    "        <input type=\"datetime-local\" name=\"ArrivalTime\"/><br>\n" +
                    "        <input type=\"submit\"/>\n" +
                    "    </form>");
        }
    }

    public void init(){
        flightDao = new FlightDao(new Configuration().configure().buildSessionFactory());
    }
}
