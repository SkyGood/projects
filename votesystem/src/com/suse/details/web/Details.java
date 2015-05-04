package com.suse.details.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suse.details.response.DetailsResponse;
import com.suse.details.service.DetailService;

@WebServlet("/Details")
public class Details extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			DetailsResponse details = new DetailService().details(id);
			request.setAttribute("info", details);
			request.getRequestDispatcher("/WEB-INF/details.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
