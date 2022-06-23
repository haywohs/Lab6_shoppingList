package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("logout")) {
                session.invalidate();
                session = request.getSession();
            } else if (session.getAttribute("username") != null) {
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        return;
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("itemList");
        if (itemList == null) {
            itemList = new ArrayList<String>();
        }
        
        if (action != null) {
            switch (action) {
                case "register":
                    String username = request.getParameter("username");
                    if(username==null || username.equals("")){
                        String error = "Please enter the username.";
                        request.setAttribute("error", error);
                        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                    return;
                    }
                    session.setAttribute("username", username);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                    return;
                case "add":
                    String item = request.getParameter("item");
                    if (item != null && !item.equals("")) {
                        itemList.add(item);
                    }
                    session.setAttribute("itemList", itemList);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                    return;
                case "delete":
                    item = request.getParameter("item");
                    if (item != null) {
                        itemList.remove(item);
                    }
                    session.setAttribute("itemList", itemList);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                    return;
            }
            
        }
        
    }
    
}
