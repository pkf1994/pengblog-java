package com.pengblog.bean;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable{
	
	private Integer comment_id;
	
	private Visitor comment_author;
	
	private Integer comment_referComment;
	
	private String comment_content;
	
	private Date comment_releaseTime;
	
	private Integer comment_hostId;

	/**
	 * @return the comment_id
	 */
	public Integer getComment_id() {
		return comment_id;
	}

	/**
	 * @param comment_id the comment_id to set
	 */
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}


	public Visitor getComment_author() {
		return comment_author;
	}

	public void setComment_author(Visitor comment_author) {
		this.comment_author = comment_author;
	}

	/**
	 * @return the comment_content
	 */
	public String getComment_content() {
		return comment_content;
	}

	/**
	 * @param comment_content the comment_content to set
	 */
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}


	


	public Integer getComment_referComment() {
		return comment_referComment;
	}

	public void setComment_referComment(Integer comment_referComment) {
		this.comment_referComment = comment_referComment;
	}

	public Date getComment_releaseTime() {
		return comment_releaseTime;
	}

	public void setComment_releaseTime(Date comment_releaseTime) {
		this.comment_releaseTime = comment_releaseTime;
	}

	

	public Comment(Integer comment_id, Visitor comment_author, Integer comment_referComment, String comment_content,
			Date comment_releaseTime, Integer comment_hostId) {
		super();
		this.comment_id = comment_id;
		this.comment_author = comment_author;
		this.comment_referComment = comment_referComment;
		this.comment_content = comment_content;
		this.comment_releaseTime = comment_releaseTime;
		this.comment_hostId = comment_hostId;
	}

	/**
	 * @return the comment_hostId
	 */
	public Integer getComment_hostId() {
		return comment_hostId;
	}

	/**
	 * @param comment_hostId the comment_hostId to set
	 */
	public void setComment_hostId(Integer comment_hostId) {
		this.comment_hostId = comment_hostId;
	}

	/**
	 * 
	 */
	public Comment() {
		super();
	}

}
