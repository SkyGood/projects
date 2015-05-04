package com.suse.vote.domain.response;

import java.sql.Timestamp;

public class VoteListResponse {

	private String ip;

	private Timestamp lastTime;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
}
