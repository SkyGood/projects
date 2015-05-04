package com.suse.vote.dao;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.suse.vote.domain.VoteInfo;

public class VoteDao {
	
	public void addVote(int id, QueryRunner qr) throws SQLException {
		qr.update(SqlMapping.VOTE_BY_ID, id);
	}

	public void addInfo(String ip, QueryRunner qr) throws SQLException {
		qr.update(SqlMapping.ADD_VOTE_INFO, ip);
	}

	public Boolean isAbleVote(String ip, QueryRunner qr) throws SQLException {
		 VoteInfo info =  (VoteInfo) qr.query(SqlMapping.LAST_VOTE_INFO, ip, new BeanHandler(VoteInfo.class));
		//若没有投过票
		if (info == null) return true;
		Long lastTime = info.getLastTime().getTime();
		long middle = (System.currentTimeMillis() - lastTime) / 1000;
		//若在60秒内已经投过
		if (middle <= 60) {
			return false; 
		}
		return true;
	}
}
