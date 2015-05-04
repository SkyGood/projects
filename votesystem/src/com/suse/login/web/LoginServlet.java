package com.suse.login.web;

import java.io.IOException;

import javax.management.modelmbean.RequiredModelMBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suse.login.service.LoginService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Boolean flag = new LoginService().login(username, password);
		if (!flag) {
			String message = "登录失败！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("username", username);
			response.sendRedirect("/votesystem/AllServlet");
		}
	}

}
