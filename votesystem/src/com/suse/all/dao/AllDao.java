package com.suse.all.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.suse.all.response.AllResponse;
import com.suse.utils.C3P0Utils;

public class AllDao {

	public List<AllResponse> all() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		List<AllResponse> list = (List<AllResponse>) qr.query(SqlMapping.FIND_ALL, new BeanListHandler(AllResponse.class));
		return list;
	}

}
