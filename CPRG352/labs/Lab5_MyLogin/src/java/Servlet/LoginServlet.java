package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("logout") != null) {
            String logoutMessage = "You have successfully logged out.";
            session.invalidate();
            request.setAttribute("logout", logoutMessage);
        } else if (session.getAttribute("username") != null) {
            response.sendRedirect("home");
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");

        AccountService as = new AccountService();
        if (name == null || pwd == null || name.equals("") || pwd.equals("")) {
            String error = "Please enter username and password.";
            request.setAttribute("fail", error);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        } else {
            session.setAttribute("username", name);
            session.setAttribute("password", pwd);
            if (as.login(name, pwd) == null) {
                String fail = "Failed Authentication ";
                request.setAttribute("fail", fail);
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            } else {
                response.sendRedirect("home");
                return;
            }
        }
    }

}
