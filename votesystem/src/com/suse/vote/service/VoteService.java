package com.suse.vote.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;

import com.suse.exception.VoteTimeException;
import com.suse.utils.C3P0Utils;
import com.suse.vote.dao.VoteDao;

public class VoteService {
	private DataSource dataSource = C3P0Utils.getDataSource();
	private Connection conn;
	private Boolean flag = true;
	private QueryRunner qr = new QueryRunner(dataSource);
	
	public VoteService() {
		try {
			this.conn = dataSource.getConnection();
		} catch (Exception e) {
			flag = false;
		}
	}
	
	public Boolean vote(int id, String ip) throws VoteTimeException {
		VoteDao voteDao = new VoteDao();
		
		try {
			Boolean ableVote = voteDao.isAbleVote(ip, qr);
			if (!ableVote)	throw new VoteTimeException();
			conn.setAutoCommit(false);
			voteDao.addVote(id, qr);
			voteDao.addInfo(ip, qr);
			conn.commit();
		} catch (SQLException e) {
			flag = false;
			try {
				conn.rollback();
				conn.commit();
			} catch (Exception e1) {
			}
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				flag = false;
			}
		}
		
		return flag;
	}

}
