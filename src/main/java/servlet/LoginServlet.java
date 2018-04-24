package servlet;

import dao.UserDao;
import entities.User;
import org.hibernate.cfg.Configuration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Vlad on 23-Oct-17.
 */
@WebServlet(name = "login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao;

    public LoginServlet() {
        this.userDao = new UserDao(new Configuration().configure().buildSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
        //dispatcher.forward(request,response);
        response.setContentType("text/html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = null;
        User user = userDao.findUser(username);
        Cookie cookie = null;
        if(user != null) {
            if (password.equals(user.getPassword())) {
                session = request.getSession();
                cookie = new Cookie("Role",user.getRole());
                response.addCookie(cookie);
                session.setAttribute("User", user);
                if (user.getRole().equals("USER")) {
                    response.sendRedirect("user.html");
                } else if (user.getRole().equals("ADMIN")) {
                    response.sendRedirect("admin.html");
                }
            }
            else
                response.sendError(403);
        }
        else
            response.sendError(403);
    }

    public void init() throws ServletException {
        //do nothing
    }

    public void destroy() {
        //do nothing
    }
}
