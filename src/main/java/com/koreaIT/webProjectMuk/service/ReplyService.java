package com.koreaIT.webProjectMuk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreaIT.webProjectMuk.dao.ReplyDao;
import com.koreaIT.webProjectMuk.vo.Reply;

@Service
public class ReplyService {
	
	private ReplyDao replyDao;
	
	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	public void writeReply(int loginedMemberId, String relTypeCode, int relId, String body) {
		this.replyDao.writeReply(loginedMemberId, relTypeCode, relId, body);
	}
	
	public List<Reply> getReplies(String relTypeCode, int relId) {
		return replyDao.getReplies(relTypeCode, relId);
	}

	public int getLastInsertId() {
		return this.replyDao.getLastInsertId();
	}

	public void deleteReply(int id) {
		replyDao.deleteReply(id);
	}
	
	public Reply getReplyById(int id) {
		return this.replyDao.getReplyById(id);
	}

	public void modifyReply(int id, String body) {
		this.replyDao.modifyReply(id, body);
	}
}