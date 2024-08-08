package com.koreaIT.webProjectMuk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String writerName;
	private String relTypeCode;
	private int relId;
	private String body;
	
	public String getForPrintBody() {
		return this.body.replaceAll("\n", "<br/>");
	}
}
