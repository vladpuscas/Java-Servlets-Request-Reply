package servlet;

import dao.UserDao;
import entities.User;

import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vlad on 24-Oct-17.
 */
@WebServlet(name = "add")
public class AddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user1 = new User();
        user1.setRole("USER");
        user1.setPassword("user");
        user1.setUsername("user");
        UserDao userDao = new UserDao(new Configuration().configure().buildSessionFactory());
        userDao.addUser(user1);
        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword("admin");
        user2.setRole("ADMIN");
        userDao.addUser(user2);
        response.sendRedirect("index.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Processing request...");
    }
}
