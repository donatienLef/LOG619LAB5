package com.pro.servlets;

import com.pro.servlets.abstracts.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Home extends AbstractServlet {

    public static final String VUE = "/WEB-INF/Home.jsp";


    public Home() {
        super(VUE);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/Identification");
    }

}
