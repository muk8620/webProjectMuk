package com.koreaIT.webProjectMuk.service;

import org.springframework.stereotype.Service;

import com.koreaIT.webProjectMuk.dao.MemberDao;
import com.koreaIT.webProjectMuk.vo.Member;

@Service
public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void joinMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		memberDao.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}
	
	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}
	
	public int getLastInsertId() {
		return memberDao.getLastInsertId();
	}

	public void memberModify(int loginedMemberId, String name, String nickname, String cellphoneNum, String email) {
		this.memberDao.memberModify(loginedMemberId, name, nickname, cellphoneNum, email);
	}

	public void doPasswordModify(int loginedMemberId, String loginPw) {
		this.memberDao.doPasswordModify(loginedMemberId, loginPw);
	}

}
