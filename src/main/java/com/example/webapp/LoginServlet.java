package com.example.webapp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Login page request " + new Date());
        request.getRequestDispatcher("login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String userName = request.getParameter("first");
        String userPassword = request.getParameter("password");

        //Check in DB

        if (userName.equalsIgnoreCase("John")) {
            if (userPassword.equals("1234")) {
                response.getWriter().println("Welcome back, dear " + userName);
                return;
            } else {
                // include

                response.getWriter().println("<h2>Incorrect username or password</h2>");
                RequestDispatcher rd = request.getRequestDispatcher("/login.html");
                rd.include(request, response);

                return;
            }
        }
    }
}
