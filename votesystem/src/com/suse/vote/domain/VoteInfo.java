package com.suse.vote.domain;

import java.sql.Timestamp;

public class VoteInfo {

	private Timestamp lastTime;

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

}
