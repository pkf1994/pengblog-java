/**
 * 
 */
package com.pengblog.api;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pengblog.bean.Comment;
import com.pengblog.bean.Visitor;
import com.pengblog.service.IcommentService;
import com.pengblog.service.IvisitorService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	@Qualifier("commentService")
	private IcommentService commentService;
	
	@Autowired
	@Qualifier("visitorService")
	private IvisitorService visitorService;

	@RequestMapping(value="/comment_list.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getCommentList(int article_id, int currentPage, int pageScale) {
		
		int maxPage = commentService.getMaxPage(article_id, pageScale);
		
		int countOfComment = commentService.getCountOfComment(article_id);
		
		Comment[] commentList = commentService.getCommentList(article_id, currentPage, pageScale);
		
		Gson gson = new Gson();
		Map<String,Object> ret = new HashMap<>();
		ret.put("maxPage", maxPage);
		ret.put("commentList", commentList);
		ret.put("countOfComment", countOfComment);
		String retJson = gson.toJson(ret);
		
		System.out.println(retJson);
		
		return retJson;
	}
	
	@RequestMapping(value="/comment.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getComment(int comment_id) {
		
		Comment ret = commentService.getCommentById(comment_id);
		
		Gson gson = new Gson();
		
		String retJson = gson.toJson(ret);
		
		return retJson;
	}
	
	@RequestMapping(value="/submit_comment.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object submitComment(@RequestBody Map<String, String> commentData) throws UnsupportedEncodingException {
		
		Visitor visitor = new Visitor();
		
		Comment comment = new Comment();
		
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
			comment.setComment_hostId(Integer.parseInt(commentData.get("comment_hostId")));
		}
		
		if(commentData.containsKey("visitor_email") && (commentData.get("visitor_email")!="")) {
			visitor.setVisitor_email(commentData.get("visitor_email"));
		}
		
		if(commentData.containsKey("visitor_siteAddress") && (commentData.get("visitor_siteAddress")!="")) {
			visitor.setVisitor_siteAddress(commentData.get("visitor_siteAddress"));
		}
		
		comment.setComment_author(visitor);
		
		comment.setComment_releaseTime(new Date());
		
		int visitor_id = visitorService.saveVisitor(visitor);
		
		int comment_id = commentService.saveComment(comment);
		
		if(visitor_id == 1 && comment_id == 1) {
			return "submit comment successful";
		}
		
		return "submit comment fail";
		
	}
}


