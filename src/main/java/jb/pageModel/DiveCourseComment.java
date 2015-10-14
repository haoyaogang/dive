package jb.pageModel;

import java.util.Date;

public class DiveCourseComment implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String courseId;	
	private java.lang.String comment;	
	private java.lang.String pid;	
	private java.lang.String userId;	
	private Date addtime;			

	private DiveAccount commentUser;
	private DiveAccount parentCommentUser;

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setCourseId(java.lang.String courseId) {
		this.courseId = courseId;
	}
	
	public java.lang.String getCourseId() {
		return this.courseId;
	}
	public void setComment(java.lang.String comment) {
		this.comment = comment;
	}
	
	public java.lang.String getComment() {
		return this.comment;
	}
	public void setPid(java.lang.String pid) {
		this.pid = pid;
	}
	
	public java.lang.String getPid() {
		return this.pid;
	}
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}

	public DiveAccount getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(DiveAccount commentUser) {
		this.commentUser = commentUser;
	}

	public DiveAccount getParentCommentUser() {
		return parentCommentUser;
	}

	public void setParentCommentUser(DiveAccount parentCommentUser) {
		this.parentCommentUser = parentCommentUser;
	}

}
