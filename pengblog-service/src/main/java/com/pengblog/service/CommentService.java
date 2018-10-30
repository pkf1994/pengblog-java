/**
 * 
 */
package com.pengblog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pengblog.bean.Article;
import com.pengblog.bean.Comment;
import com.pengblog.bean.Visitor;
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

	@Override
	public Comment constructComment(Map<String, String> commentData) {
		
		Comment comment = new Comment();
		
		Visitor visitor = new Visitor();
		
		Article article = new Article();
		
		if(commentData.containsKey("visitor_name") && (commentData.get("visitor_name")!="")) {
			visitor.setVisitor_name(commentData.get("visitor_name"));
		}
		
		if(commentData.containsKey("comment_referComment") && (commentData.get("comment_referComment")!="")) {
			comment.setComment_referComment(Integer.parseInt(commentData.get("comment_referComment")));
		}
		
		if(commentData.containsKey("comment_content") && (commentData.get("comment_content")!="")) {
			comment.setComment_content(commentData.get("comment_content"));
		}
		
		if(commentData.containsKey("comment_hostId") && (commentData.get("comment_hostId")!="")) {
			
			article.setArticle_id(Integer.parseInt(commentData.get("comment_hostId")));

		}
		
		if(commentData.containsKey("visitor_email") && (commentData.get("visitor_email")!="")) {
			visitor.setVisitor_email(commentData.get("visitor_email"));
		}
		
		if(commentData.containsKey("visitor_siteAddress") && (commentData.get("visitor_siteAddress")!="")) {
			visitor.setVisitor_siteAddress(commentData.get("visitor_siteAddress"));
		}
		
		comment.setComment_hostArticle(article);
		
		comment.setComment_author(visitor);
		
		comment.setComment_releaseTime(new Date());
		
		return comment;
	}
}
