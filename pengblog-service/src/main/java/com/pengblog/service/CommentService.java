/**
 * 
 */
package com.pengblog.service;

import java.util.List;

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
		
		int countOfComment = commentDao.selectCountOfCommentByHostId(hostId);
		
		int maxPage = (int) Math.ceil((double)(countOfComment/pageScale)) + 1;
				
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

	@Override
	public int getCountOfComment(int hostId) {
		
		int countOfComment = commentDao.selectCountOfCommentByHostId(hostId);
		
		return countOfComment;
	}

	@Override
	public Comment getCommentById(int comment_id) {
		
		Comment comment = commentDao.selectCommentById(comment_id);
		
		return comment;
	}

	@Override
	public int saveComment(Comment comment) {
		
		int comment_id = commentDao.insertComment(comment);
		
		return comment_id;
	}

	@Override
	public List<Comment> getCommentLastList(int listScale) {
		
		List<Comment> retList = commentDao.selectCommentLastList(listScale);
		
		return retList;
	}
}
