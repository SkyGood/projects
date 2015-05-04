package com.suse.vote.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suse.exception.VoteTimeException;
import com.suse.vote.service.VoteService;

@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = new Integer(request.getParameter("id"));
		String ip = request.getRemoteAddr();
		Boolean flag = true;
		try {
			flag = new VoteService().vote(id, ip);
		} catch (VoteTimeException e) {
			request.setAttribute("message", "一分钟内只允许投一次票!");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
			return ;
		}
		if (flag) {
			response.sendRedirect(request.getContextPath()+"/AllServlet");
		} else {
			request.setAttribute("message", "投票失败!");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
		}
	}

	
}
