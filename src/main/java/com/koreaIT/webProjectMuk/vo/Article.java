package com.koreaIT.webProjectMuk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	private int memberId;
	private int boardId;
	private String writerName;
	private int views;
	private int likePoint;
	
	public String getForPrintBody() {
		return this.body.replaceAll("\n", "<br/>");
	}
}
