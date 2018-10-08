package com.pengblog.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pengblog.bean.Article;
import com.pengblog.service.IarticleService;

/**
 * @author Peng Kaifan
 * 与article有关的接口
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
		
			//获取文章列表
			Article[] articleList = articleService.getArticleSummaryList(currentPage,pageScale);
			
		/*	for (int i = 0; i < articleList.length; i++) {
				System.out.println(articleList[i].getArticle_title());
			}*/
		
			//根据页面规模获取总页数
			int maxPage = articleService.getMaxPage(pageScale);
			
			//组装json
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
	
}
