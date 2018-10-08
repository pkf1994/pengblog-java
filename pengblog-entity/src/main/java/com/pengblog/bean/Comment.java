package com.pengblog.bean;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable{
	
	private Integer comment_id;
	
	private String comment_author;
	
	private Integer comment_refercomment;
	
	private String comment_content;
	
	private Date comment_releasetime;
	
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

	/**
	 * @return the comment_author
	 */
	public String getComment_author() {
		return comment_author;
	}

	/**
	 * @param comment_author the comment_author to set
	 */
	public void setComment_author(String comment_author) {
		this.comment_author = comment_author;
	}

	/**
	 * @return the comment_refercomment
	 */
	public Integer getComment_refercomment() {
		return comment_refercomment;
	}

	/**
	 * @param comment_refercomment the comment_refercomment to set
	 */
	public void setComment_refercomment(Integer comment_refercomment) {
		this.comment_refercomment = comment_refercomment;
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

	/**
	 * @return the comment_releasetime
	 */
	public Date getComment_releasetime() {
		return comment_releasetime;
	}

	/**
	 * @param comment_releasetime the comment_releasetime to set
	 */
	public void setComment_releasetime(Date comment_releasetime) {
		this.comment_releasetime = comment_releasetime;
	}
	


	/**
	 * @param comment_id
	 * @param comment_author
	 * @param comment_refercomment
	 * @param comment_content
	 * @param comment_releasetime
	 * @param comment_hostId
	 */
	public Comment(Integer comment_id, String comment_author, Integer comment_refercomment, String comment_content, Date comment_releasetime, Integer comment_hostId) {
		super();
		this.comment_id = comment_id;
		this.comment_author = comment_author;
		this.comment_refercomment = comment_refercomment;
		this.comment_content = comment_content;
		this.comment_releasetime = comment_releasetime;
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
