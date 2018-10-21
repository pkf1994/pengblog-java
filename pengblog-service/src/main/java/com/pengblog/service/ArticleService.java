package com.pengblog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengblog.bean.Article;
import com.pengblog.dao.IarticleDao;
import com.pengblog.utils.ArticleFields;
import com.pengblog.utils.MyHtmlUtil;

/**
 * @author Administrator
 *
 */
@Service("articleService")
public class ArticleService implements IarticleService{
	
	@Autowired
	private IarticleDao articleDao;
	
	@Autowired
	@Qualifier("qiniuService")
	private IqiniuService qiniuService;
	
	

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

			
		Article[] articleList = articleDao.selectArticleListByLimitIndex(startIndex,pageScale,paramList,"article");
		
		return articleList;
	}

	/* (non-Javadoc)
	 * @see com.pengblog.service.IarticleService#getMaxPage(int)
	 */
	@Transactional
	public int getMaxPage(int pageScale) {
		
		int countOfAllArticle = articleDao.selectCountOfArticle("article");
		
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

	@Override
	public Article constructArticle(Map<String, String> articleData) {
		
		Article article = new Article();
		
		if(articleData.containsKey("article_id") && (articleData.get("article_id")!="")) {
			article.setArticle_id(Integer.parseInt(articleData.get("article_id")));
		}
		
		if(articleData.containsKey("article_title") && (articleData.get("article_title")!="")) {
			article.setArticle_title(articleData.get("article_title"));
		}
		
		if(articleData.containsKey("article_author") && (articleData.get("article_author")!="")) {
			article.setArticle_author(articleData.get("article_author"));
		}
		
		if(articleData.containsKey("article_label") && (articleData.get("article_label")!="")) {
			article.setArticle_label(articleData.get("article_label"));
		}
		
		if(articleData.containsKey("article_content") && (articleData.get("article_content")!="")) {
			
			article.setArticle_content(articleData.get("article_content"));
			
			String article_content = articleData.get("article_content");
			
			Document doc = Jsoup.parse(article_content);
			
			String article_content_text = doc.body().text();
			
			int length = article_content_text.length();
			
			if(length <= 200) {
				
				String article_summary = article_content_text;
				
				article.setArticle_summary(article_summary);
				
			}else {
				String article_summary = article_content_text.substring(0,200);
				
				article.setArticle_summary(article_summary);
			}
			
		}
		
		if(articleData.containsKey("article_type") && (articleData.get("article_type")!="")) {
			article.setArticle_type(articleData.get("article_type"));
		}
		
		article.setArticle_releaseTime(new Date());
		
		return article;
	}

	public Article handleImageUrl(Article article) {
		
		List<String> imgUrls = MyHtmlUtil.extractImageUrlFromArticleContent(article.getArticle_content());
		
		List<String> handledImgUrls = qiniuService.handleImageUrl(imgUrls);
		
		String article_content = article.getArticle_content();
		
		for(int i = 0; i < imgUrls.size(); i++) {
			article_content.replace(imgUrls.get(i), handledImgUrls.get(i));
		}
		
		article.setArticle_content(article_content);
		
		return article;
	}

	@Override
	public int saveArticle(Article article) {
		
		int article_id = articleDao.insertArticle(article);
		
		return article_id;
	}

	@Override
	public Article[] getDraftList(int currentPage, int pageScale) {
		int startIndex = (currentPage - 1) * pageScale;
		
		List<String> paramList = new ArrayList<>();
		
		paramList.add(ArticleFields.ARTICLE_ID.fieldName);
		paramList.add(ArticleFields.ARTICLE_TITLE.fieldName);
		paramList.add(ArticleFields.ARTICLE_RELEASETIME.fieldName);

			
		Article[] articleList = articleDao.selectArticleListByLimitIndex(startIndex,pageScale,paramList,"draft");
		
		return articleList;
	}

	@Override
	public int getMaxPageOfDraft(int pageScale) {
		
		int countOfAllArticle = articleDao.selectCountOfArticle("draft");
		
		int maxPage = (int) Math.ceil((double)(countOfAllArticle/pageScale)) + 1;
		
		return maxPage;
	}
	
	
	
}
