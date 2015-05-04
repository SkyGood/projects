package com.suse.all.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.suse.all.dao.AllDao;
import com.suse.all.response.AllResponse;

public class AllService {

	public List<AllResponse> all() {
		List<AllResponse> list = new ArrayList<AllResponse>();
		AllDao allDao = new AllDao();
		try {
			list = allDao.all();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
