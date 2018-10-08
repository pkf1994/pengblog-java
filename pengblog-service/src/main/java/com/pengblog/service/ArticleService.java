package com.pengblog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengblog.bean.Article;
import com.pengblog.dao.IarticleDao;
import com.pengblog.num.ArticleFields;

/**
 * @author Administrator
 *
 */
@Service("articleService")
public class ArticleService implements IarticleService{
	
	@Autowired
	private IarticleDao articleDao;

	/* (non-Javadoc)
	 * @see com.pengblog.service.IarticleService#getArticleSummary(int, int)
	 */
	@Transactional
	public Article[] getArticleSummaryList(int currentPage, int pageScale) {
		
		int startIndex = (currentPage - 1) * pageScale;
		
		List<String> paramList = new ArrayList<>();
		
		paramList.add(ArticleFields.ARTICLE_ID.fieldName);
		paramList.add(ArticleFields.ARTICLE_TITLE.fieldName);
		paramList.add(ArticleFields.ARTICLE_AUTHOR.fieldName);
		paramList.add(ArticleFields.ARTICLE_SUMMARY.fieldName);
		paramList.add(ArticleFields.ARTICLE_RELEASETIME.fieldName);
		paramList.add(ArticleFields.ARTICLE_LABEL.fieldName);

			
		Article[] articleList = articleDao.selectArticleListByLimitIndex(startIndex,pageScale,paramList);
		
		return articleList;
	}

	/* (non-Javadoc)
	 * @see com.pengblog.service.IarticleService#getMaxPage(int)
	 */
	@Transactional
	public int getMaxPage(int pageScale) {
		
		int countOfAllArticle = articleDao.selectCountOfArticle();
		
		int maxPage = (int) Math.ceil((double)(countOfAllArticle/pageScale)) + 1;
		
		return maxPage;
	}

	/* (non-Javadoc)
	 * @see com.pengblog.service.IarticleService#getArticleById(int)
	 */
	@Override
	public Article getArticleById(int article_id) {
		
		Article article = articleDao.selectArticleById(article_id);
		
		return article;
	}
	
}
