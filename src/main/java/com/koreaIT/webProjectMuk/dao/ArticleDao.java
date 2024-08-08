package com.koreaIT.webProjectMuk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.koreaIT.webProjectMuk.vo.Article;

@Mapper
public interface ArticleDao {
	
	@Insert("""
			insert into article
				set regDate = now()
					, updateDate = now()
					, memberId = #{loginedMemberId}
					, boardId = #{boardId}
					, title = #{title}
					, `body` = #{body}
			""")
	public void writeArticle(int loginedMemberId, int boardId, String title, String body);
	
	@Select("""
			<script>
			SELECT a.* 
					, m.nickname writerName
					, IFNULL(SUM(lp.point) , 0) likePoint
				FROM article a 
				INNER join `member` m
				on a.memberId = m.id
				LEFT JOIN likePoint lp
				ON a.id = lp.relId
				AND relTypeCode = 'article'
				WHERE A.boardId = #{boardId}
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchKeywordType == 'title'">
							AND a.title like CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'body'">
							AND a.body like CONCAT('%', #{searchKeyword}, '%') 
						</when>
						<otherwise>
							AND (
								a.title like CONCAT('%', #{searchKeyword}, '%')
								OR a.body like CONCAT('%', #{searchKeyword}, '%')
							) 
						</otherwise>
					</choose>
				</if>
				GROUP BY a.id
				ORDER BY a.id DESC
				LIMIT #{limitFrom}, #{itemsInAPage}
			</script>
			""")
	public List<Article> getArticles(int boardId, String searchKeywordType, String searchKeyword, int limitFrom, int itemsInAPage);
	
	@Select("""
			SELECT a.* 
					, m.nickname writerName
					, IFNULL(SUM(lp.point) , 0) likePoint
				FROM article a 
				inner join `member` m
				on a.memberId = m.id
				LEFT JOIN likePoint lp
				ON a.id = lp.relId
				AND relTypeCode = 'article'
				WHERE a.id = #{id}
				GROUP BY a.id
			""")
	public Article forPrintArticle(int id);
	
	@Select("""
			SELECT *
				FROM article 
				WHERE id = #{id}
			""")
	public Article getArticleById(int id);
	
	@Update("""
			<script>
			UPDATE article 
				SET updateDate = now()
					<if test="title != null and title != ''">
						, title = #{title}
					</if>
					<if test="body != null and body != ''">
						, `body` = #{body} 
					</if>
				where id = #{id}
			</script>
			""")
	public void modifyArticle(int id, String title, String body);
	
	@Delete("""
			DELETE FROM article 
				WHERE id = #{id}
			""")
	public void deleteArticle(int id);
	
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();
	
	@Select("""
			SELECT `name`
				FROM board
				WHERE id = #{boardId}
			""")
	public String getBoardNameById(int boardId);
	
	@Select("""
			<script>
			select count(id)
				from article
				where boardId = #{boardId}
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchKeywordType == 'title'">
							AND title like CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'body'">
							AND `body` like CONCAT('%', #{searchKeyword}, '%') 
						</when>
						<otherwise>
							AND (
								title like CONCAT('%', #{searchKeyword}, '%')
								OR `body` like CONCAT('%', #{searchKeyword}, '%')
							) 
						</otherwise>
					</choose>
				</if>
			</script>
			""")
	public int getArticlesCnt(int boardId, String searchKeywordType, String searchKeyword);

	@Update("""
			UPDATE article 
				SET views = views + 1
				where id = #{id}
			""")
	public void increaseView(int id);

}
