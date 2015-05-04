package com.suse.vote.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.suse.utils.C3P0Utils;
import com.suse.vote.domain.response.VoteListResponse;

public class VoteListDao {
	private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	public List<VoteListResponse> findAllVote() throws SQLException {
		return (List<VoteListResponse>) qr.query(SqlMapping.VOTER_LIST, new BeanListHandler(VoteListResponse.class));
	}
}
