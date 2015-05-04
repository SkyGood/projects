package com.suse.details.dao;

import java.sql.Connection;

public final class SqlMapping {

	public static final String DETAIL_BY_ID = "SELECT t2.honerName, t1.age, t1.description FROM ticket.content t1 INNER JOIN ticket.vote t2 ON t1.vid = t2.id WHERE t2.id = ?";
	
}
