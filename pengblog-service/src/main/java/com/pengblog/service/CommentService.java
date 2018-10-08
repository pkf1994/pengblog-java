/**
 * 
 */
package com.pengblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pengblog.bean.Comment;
import com.pengblog.dao.IcommentDao;

/**
 * @author Administrator
 *
 */
@Service("commentService")
public class CommentService implements IcommentService{
	
	@Autowired
	private IcommentDao commentDao;
	

	public int getMaxPage(int hostId, int pageScale) {
		int maxPage = commentDao.selectMaxPage(hostId, pageScale);
		return maxPage;
	}

	/* (non-Javadoc)
	 * @see com.pengblog.service.IcommentService#getCommentList(int, int, int)
	 */
	@Override
	public Comment[] getCommentList(int hostId, int currentPage, int pageScale) {
		
		int startIndex = (currentPage - 1) * pageScale;
		
		Comment[] commentList = commentDao.selectCommentListByLimitIndex(hostId, startIndex, pageScale);
		
		return commentList;
	}
}
