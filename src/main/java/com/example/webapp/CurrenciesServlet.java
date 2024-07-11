package com.example.webapp;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.example.webapp.ServletUtils.*;

@WebServlet(name = "CurrenciesServlet", value = "/rate-request")
public class CurrenciesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String path = "currencies_request_form.html";

        if (StringUtils.isEmpty(request.getParameter("amount"))) {
            //0. show currencies request form
            forward(path, request, response);
            return;
        }
        //1. amount and currency are presented -> client request to calculate data
        //1.1 get param values
        long amount = getLongParameter(request, "amount");
        String code = request.getParameter("code");
        String code2 = request.getParameter("code2");

        //1.2 get currency rate
        double rate = Double.parseDouble(XMLCurrencyParser.getCurrency(code));
        double rate2 = Double.parseDouble(XMLCurrencyParser.getCurrency(code2));
        if (code.equals("643")) {
            rate /= 100;
        }
        if (code2.equals("643")) {
            rate2 /= 100;
        }


        //1.3 calc final amount
        double result;
        if (code2.equals("1")) {
            result = amount * rate;
        } else if (code.equals("1")) {
            result = amount / rate2;
        } else {
            result = amount * rate / rate2;
        }

        //1.4 make response
        include(path, "Result amount: " + result, request, response);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
