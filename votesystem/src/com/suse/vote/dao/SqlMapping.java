package com.suse.vote.dao;

public final class SqlMapping {

	public static final String VOTE_BY_ID = "UPDATE ticket.vote SET ticketNums = ticketNums + 1 WHERE id = ?";
	public static final String ADD_VOTE_INFO = "INSERT INTO ticket.info(ip) VALUES(?)";
	public static final String LAST_VOTE_INFO = "SELECT MAX(votetime) AS lastTime FROM ticket.info WHERE ip = ? GROUP BY ip";
	public static final String VOTER_LIST = "SELECT ip, MAX(voteTime) AS lastTime FROM ticket.info GROUP BY ip";

}
