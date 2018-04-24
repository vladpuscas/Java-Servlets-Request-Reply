package servlet;

import dao.FlightDao;
import entities.Flight;
import org.hibernate.cfg.Configuration;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Vlad on 30-Oct-17.
 */
@WebServlet(name = "updateFlight")
public class UpdateFlightServlet extends HttpServlet {
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
        Integer id = Integer.parseInt(request.getParameter("id"));
        flightDao.updateFlight(flight,id);
        response.sendRedirect("admin.html");
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
            if(request.getParameter("id") != null) {
                Flight flight = flightDao.getFlight(Integer.parseInt(request.getParameter("id")));
                response.getWriter().println("<form action=\"updateFlight\" method=\"post\">\n" +
                        "        Airplane type:<br>\n" +
                        "        <input type=\"text\" name=\"AirplaneType\" value=\"" + flight.getAirplaneType() + "\"/> <br>\n" +
                        "        Arrival City ID:<br>\n" +
                        "        <input type=\"text\" name=\"ArrivalCityId\" value=\""+ flight.getArrivalCityId() + "\"/><br>\n" +
                        "        Departure City ID:<br>\n" +
                        "        <input type=\"text\" name=\"DepartureCityId\" value=\"" + flight.getDepartureCityId() + "\"/> <br>\n" +
                        "        Departure time:<br>\n" +
                        "        <input type=\"datetime-local\" name=\"DepartureTime\" value=\"" + flight.getDepartureDate() + "\"/> <br>\n" +
                        "        Arrival time: <br>\n" +
                        "        <input type=\"datetime-local\" name=\"ArrivalTime\" value=\"" + flight.getArrivalDate() + "\"/><br>\n" +
                        "        <input type=\"hidden\" name=\"id\" value=\"" + flight.getId() + "\">" +
                        "        <input type=\"submit\"/>\n" +
                        "    </form>");
            }
            else {
                response.getWriter().println("<form action=\"updateFlight\" method=\"get\">\n" +
                        "        Flight id: <br>\n" +
                        "        <input type=\"number\" name=\"id\"><br>\n" +
                        "        <input type=\"submit\">\n" +
                        "    </form>");
            }
        }
    }

    public void init() {
        flightDao = new FlightDao(new Configuration().configure().buildSessionFactory());
    }
}
