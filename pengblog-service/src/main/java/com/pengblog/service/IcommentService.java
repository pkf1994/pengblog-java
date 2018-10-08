/**
 * 
 */
package com.pengblog.service;

import com.pengblog.bean.Comment;

/**
 * @author Administrator
 *
 */
public interface IcommentService {

	/**
	 * @param pageScale
	 * @return
	 */
	int getMaxPage(int hostid, int pageScale);

	/**
	 * @param currentPage
	 * @param pageScale
	 * @return
	 */
	Comment[] getCommentList(int hostid, int currentPage, int pageScale);

}
