package com.koreaIT.webProjectMuk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreaIT.webProjectMuk.dao.ArticleDao;
import com.koreaIT.webProjectMuk.vo.Article;

@Service
public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public void writeArticle(int loginedMemberId, int boardId, String title, String body) {
		articleDao.writeArticle(loginedMemberId, boardId, title, body);
	}
	
	public Article forPrintArticle(int id) {
		return articleDao.forPrintArticle(id);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public String getBoardNameById(int boardId) {
		return this.articleDao.getBoardNameById(boardId);
	}
	
	public List<Article> getArticles(int boardId, String searchKeywordType, String searchKeyword, int limitFrom, int itemsInAPage) {
		return articleDao.getArticles(boardId, searchKeywordType, searchKeyword, limitFrom, itemsInAPage);
	}
	
	public void modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);
	}

	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
	}

	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}

	public int getArticlesCnt(int boardId, String searchKeywordType, String searchKeyword) {
		return articleDao.getArticlesCnt(boardId, searchKeywordType, searchKeyword);
	}

	public void increaseView(int id) {
		articleDao.increaseView(id);
	}
}
