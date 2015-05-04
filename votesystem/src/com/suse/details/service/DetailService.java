package com.suse.details.service;

import java.sql.SQLException;

import com.suse.details.dao.DetailDao;
import com.suse.details.response.DetailsResponse;

public class DetailService {
	public DetailsResponse details(String id) throws SQLException {
		return new DetailDao().details(id);
	}
	
}
