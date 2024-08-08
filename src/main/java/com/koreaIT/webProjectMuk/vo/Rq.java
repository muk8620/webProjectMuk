package com.koreaIT.webProjectMuk.vo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.koreaIT.webProjectMuk.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	
	@Getter
	private int loginedMemberId;
	private HttpServletResponse resp;
	private HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		
		session = req.getSession();
		this.resp = resp;
		
		int loginedMemberId = 0;
		
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		this.loginedMemberId = loginedMemberId;
		
		req.setAttribute("rq", this);
	}

	public void jsPrintReplace(String msg, String uri) {
		resp.setContentType("text/html; charset=UTF-8;");
		
		try {
			resp.getWriter().append(Util.jsReplace(msg, uri));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void login(Member member) {
		this.session.setAttribute("loginedMemberId", member.getId());
	}

	public void logout() {
		this.session.removeAttribute("loginedMemberId");
	}
	
	public void init() {
	}
	
}