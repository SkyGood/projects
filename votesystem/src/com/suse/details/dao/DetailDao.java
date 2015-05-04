package com.suse.details.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.suse.details.response.DetailsResponse;
import com.suse.utils.C3P0Utils;

public class DetailDao {

	public DetailsResponse details(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		DetailsResponse response = (DetailsResponse) qr.query(SqlMapping.DETAIL_BY_ID, id, new BeanHandler(DetailsResponse.class));
		return response;
	}

}
