package com.koreaIT.webProjectMuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.webProjectMuk.service.ReplyService;
import com.koreaIT.webProjectMuk.util.Util;
import com.koreaIT.webProjectMuk.vo.Reply;
import com.koreaIT.webProjectMuk.vo.ResultData;
import com.koreaIT.webProjectMuk.vo.Rq;

@Controller
public class UsrReplyController {

	private ReplyService replyService;
	private Rq rq;

	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}

	@PostMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String body, String relTypeCode, int relId) {

		replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);

		int id = replyService.getLastInsertId();

		return Util.jsReplace(String.format("%d번 댓글을 작성했습니다", id), String.format("../article/detail?id=%d", relId));
	}
	
	@GetMapping("/usr/reply/doDelete")
	@ResponseBody
	public String doDelete(int id, int relId) {
		replyService.deleteReply(id);
		
		return Util.jsReplace("댓글을 삭제햇습니다.", String.format("../article/detail?id=%d", relId));
	}
	
	@GetMapping("/usr/reply/getReply")
	@ResponseBody
	public ResultData<Reply> getReply(int id) {
		
		Reply reply = replyService.getReplyById(id);
		
		return ResultData.from("S-1", "댓글 정보 조회 성공", reply);
	}
	
	@PostMapping("/usr/reply/doModify")
	@ResponseBody
	public String doModify(int id, int relId, String body) {
		
		replyService.modifyReply(id, body);
		
		return Util.jsReplace(String.format("%d번 댓글을 수정했습니다", id), String.format("../article/detail?id=%d", relId));
	}
}