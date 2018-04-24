package servlet;

import dao.CityDao;
import entities.City;
import jaxb.Timezone;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vlad on 29-Oct-17.
 */
@WebServlet(name = "LocalTimesServlet")
public class LocalTimesServlet extends HttpServlet {
    private CityDao cityDao;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        City arrival = cityDao.getCity(Integer.parseInt(request.getParameter("arrivalId")));
        City departure = cityDao.getCity(Integer.parseInt(request.getParameter("departureId")));

        String url1 = "http://new.earthtools.org/timezone/" + arrival.getLatitude() + "/" + arrival.getLongitude();
        String url2 = "http://new.earthtools.org/timezone/" + departure.getLatitude() + "/" + departure.getLongitude();

        URL url = new URL(url1);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept","application/xml");

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Timezone.class);
            InputStream xml = connection.getInputStream();
            Timezone timezone1 = (Timezone) jaxbContext.createUnmarshaller().unmarshal(xml);

            connection.disconnect();

            url = new URL(url2);
            connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/xml");

            jaxbContext = JAXBContext.newInstance(Timezone.class);
            xml = connection.getInputStream();
            Timezone timezone2 = (Timezone) jaxbContext.createUnmarshaller().unmarshal(xml);

            connection.disconnect();

            response.setContentType("text/html");
            response.getWriter().println("Local time in " + departure.getName() + ": " + timezone2.getLocaltime() + "<br>" +
                    "Local time in " + arrival.getName() + ": " + timezone1.getLocaltime());



        }catch (JAXBException e) {
            response.getWriter().println("An error occurred, please try again later.");
            e.printStackTrace(response.getWriter());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void init() {
        cityDao = new CityDao(new Configuration().configure().buildSessionFactory());
    }
}
