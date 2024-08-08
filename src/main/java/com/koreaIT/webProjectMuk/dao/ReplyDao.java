package com.koreaIT.webProjectMuk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.koreaIT.webProjectMuk.vo.Reply;

@Mapper
public interface ReplyDao {
	
	@Insert("""
			INSERT INTO reply
				SET regDate = NOW()
					, updateDate = NOW()
					, memberId = #{loginedMemberId}
					, relTypeCode = #{relTypeCode}
					, relId = #{relId}
					, `body` = #{body}
			""")
	public void writeReply(int loginedMemberId, String relTypeCode, int relId, String body);
	
	@Select("""
			SELECT r.*
					, m.nickname writerName
				FROM reply r
				inner join `member` m
				on r.memberId = m.id
				WHERE r.relTypeCode = #{relTypeCode}
				AND r.relId = #{relId}
			""")
	public List<Reply> getReplies(String relTypeCode, int relId);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();
	
	@Delete("""
			DELETE FROM reply 
				WHERE id = #{id}
			""")
	public void deleteReply(int id);
	
	@Select("""
			SELECT R.*, M.nickname `writerName`
				FROM reply R
				INNER JOIN `member` M
				ON R.memberId = M.id
				WHERE R.id = #{id}
			""")
	public Reply getReplyById(int id);

	@Update("""
			UPDATE reply
				SET updateDate = NOW()
					, `body` = #{body}
				WHERE id = #{id}
			""")
	public void modifyReply(int id, String body);
}