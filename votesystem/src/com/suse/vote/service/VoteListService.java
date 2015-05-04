package com.suse.vote.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.suse.vote.dao.VoteListDao;
import com.suse.vote.domain.response.VoteListResponse;

public class VoteListService {

	public List<VoteListResponse> findAllVote() {
		try {
			return new VoteListDao().findAllVote();
		} catch (SQLException e) {
			return new ArrayList<VoteListResponse>();
		}
	}

}
