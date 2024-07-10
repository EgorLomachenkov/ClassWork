package com.example.webapp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "RDServlet", value = "/rd-test")
public class RDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("request " + request.getRequestedSessionId());
        String val = Optional.ofNullable(request.getParameter("param")).orElse("Empty");

        if (val.equals("page1")) {
            RequestDispatcher rd = request.getRequestDispatcher("page1.html");
            rd.forward(request, response);
        } else {
            response.sendRedirect("https://www.onliner.by/");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
