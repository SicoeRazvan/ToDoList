package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String u = request.getParameter("u");
        String p = request.getParameter("p");

        LoginDbOperations d = new LoginDbOperations();
        int value = d.login(u, p);

        if(value!=-1) {
            HttpSession session = request.getSession();
            session.setAttribute("userid", value);

            response.sendRedirect("index.jsp");

        }
        else
        {
            String back = "/login.html";
            HttpSession session = request.getSession();
            session.removeAttribute("userid");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
            dispatcher.forward(request, response);
        }

    }

}
