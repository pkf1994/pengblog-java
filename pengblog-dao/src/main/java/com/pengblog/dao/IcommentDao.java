/**
 * 
 */
package com.pengblog.dao;

import com.pengblog.bean.Comment;

/**
 * @author Administrator
 *
 */
public interface IcommentDao {
	
	int selectMaxPage(int hostId, int pageScale);
	
	Comment[] selectCommentListByLimitIndex(int hostId, int startIndex, int pageScale);
	
}
