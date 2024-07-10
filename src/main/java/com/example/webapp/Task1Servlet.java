package com.example.webapp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "Task1Servlet", value = "/task1")
public class Task1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("task1.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
        int age = Integer.parseInt(request.getParameter("age"));
        if (age > 17) {
            request.getRequestDispatcher("img.png").forward(request, response);
        } else {
            request.getRequestDispatcher("img_1.png").forward(request, response);
        }
    }
}
