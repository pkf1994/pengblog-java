package com.pengblog.service;


import java.util.Map;

import com.pengblog.bean.Article;

/**
 * @author Administrator
 *	article���ҵ��ӿ�
 */
public interface IarticleService {

	/**
	 * @param currentPage
	 * @param pageScale
	 * @return
	 */
	Article[] getArticleSummaryList(int currentPage, int pageScale);

	/**
	 * @param pageScale
	 * @return
	 */
	int getMaxPage(int pageScale);

	/**
	 * @param article_id
	 * @return
	 */
	Article getArticleById(int article_id);

	Article constructArticle(Map<String, String> articleData);
	
	int saveArticle(Article article);

	Article handleImageUrl(Article article);


}
