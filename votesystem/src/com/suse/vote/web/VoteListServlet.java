package com.suse.vote.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suse.vote.domain.response.VoteListResponse;
import com.suse.vote.service.VoteListService;

@WebServlet("/VoteListServlet")
public class VoteListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<VoteListResponse> list =  new VoteListService().findAllVote();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/votelist.jsp").forward(request, response);
	}
}
