/**
 * 
 */
package com.pengblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pengblog.bean.Article;

/**
 * @author Administrator
 *
 */

public interface IarticleDao {

	/**
	 * @param startIndex
	 * @param pageScale
	 * @param paramList 
	 * @return
	 */
	Article[] selectArticleListByLimitIndex(@Param("startIndex")int startIndex, @Param("pageScale")int pageScale, @Param("paramList") List<String> paramList);

	/**
	 * @return
	 */
	int selectCountOfArticle();

	/**
	 * @param article_id
	 * @return
	 */
	Article selectArticleById(int article_id);
	
}
