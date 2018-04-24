package servlet;

import dao.CityDao;
import entities.City;
import entities.User;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Vlad on 29-Oct-17.
 */
@WebServlet(name = "addCity")
public class NewCityServlet extends HttpServlet {
    private CityDao cityDao;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        City city = new City();
        city.setName(request.getParameter("CityName"));
        city.setLongitude(Double.parseDouble(request.getParameter("Longitude")));
        city.setLatitude(Double.parseDouble(request.getParameter("Latitude")));
        cityDao.addCity(city);
        response.sendRedirect("addCity");
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
            response.getWriter().println("<form action=\"addCity\" method=\"post\">\n" +
                    "        City name<br>\n" +
                    "        <input type=\"text\" name=\"CityName\"/><br>\n" +
                    "        Longitude<br>\n" +
                    "        <input type=\"text\" name=\"Longitude\"/><br>\n" +
                    "        Latitude<br>\n" +
                    "        <input type=\"text\" name=\"Latitude\"/><br>\n" +
                    "        <input type=\"submit\"/>\n" +
                    "    </form>");
        }
    }

    public void init() {
        cityDao = new CityDao(new Configuration().configure().buildSessionFactory());
    }
}
