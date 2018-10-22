package com.pengblog.api;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pengblog.bean.Article;
import com.pengblog.service.IarticleService;

/**
 * @author Peng Kaifan
 * ��article�йصĽӿ�
 *
 */

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	@Qualifier("articleService")
	private IarticleService articleService;
	
	@RequestMapping(value="/article_summary.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getArticleSummaryList(int currentPage,
										int pageScale) {
		
		Article[] articleList = articleService.getArticleSummaryList(currentPage,pageScale);
		
		int maxPage = articleService.getMaxPage(pageScale);
		
		Gson gson = new Gson();
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("articleList", articleList);
		ret.put("maxPage",maxPage);
		String retJson = gson.toJson(ret);
		
		return retJson;
	}
	
	@RequestMapping(value="/article.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getArticle(int article_id) {
		
		Article article = articleService.getArticleById(article_id);
		
		Gson gson = new Gson();
		
		String retJson = gson.toJson(article);
		
		return retJson;
	}
	
	@RequestMapping(value="/upload_article.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object uploadArticle(@RequestBody Map<String,String> articleData) {
		
		Article article = articleService.constructArticle(articleData);
		
		Article handledArticle = articleService.handleImageUrl(article);
		
		if(handledArticle.getArticle_id() != 0) {
			articleService.updateArticle(handledArticle);
		}else {
			articleService.saveArticle(handledArticle);
		}
			
		return handledArticle.getArticle_id();
	}
	
	@RequestMapping(value="/draft_list.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getDraftList(int currentPage,
								int pageScale) {
		Article[] articleList = articleService.getDraftList(currentPage,pageScale);
		
		int maxPage = articleService.getMaxPageOfDraft(pageScale);
		
		Gson gson = new Gson();
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("articleList", articleList);
		ret.put("maxPage",maxPage);
		String retJson = gson.toJson(ret);
		
		return retJson;
	}
	
	@RequestMapping(value="/delete_article.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteArticle(int article_id) {
		
		
		articleService.deleteArticleById(article_id);
		
		return "delete success";
	}
	
}
