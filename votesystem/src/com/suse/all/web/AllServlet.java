package com.suse.all.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suse.all.response.AllResponse;
import com.suse.all.service.AllService;

@WebServlet("/AllServlet")
public class AllServlet extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			AllService allService = new AllService();
			List<AllResponse> list = allService.all();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/list.jsp").forward(req, resp);
		}
}
