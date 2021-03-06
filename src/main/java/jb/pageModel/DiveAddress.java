package jb.pageModel;

import java.util.Date;

import jb.listener.Application;

public class DiveAddress implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String name;	
	private java.lang.String sumary;	
	private java.lang.String icon;	
	private java.lang.String description;	
	private java.lang.String area;	
	private java.lang.String feature;	
	private java.lang.String status;	
	private Date addtime;		
	private java.lang.String addUserId;

	private boolean isCollect;
	private boolean isPraise;
	
	private int collectNum;
	private int praiseNum;
	
	public String getStatusZh() {
		return Application.getString(this.status);
	}
	public String getAreaZh() {
		return Application.getString(this.area);
	}
	public String getFeatureZh() {
		return Application.getString(this.feature);
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setSumary(java.lang.String sumary) {
		this.sumary = sumary;
	}
	
	public java.lang.String getSumary() {
		return this.sumary;
	}
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	public java.lang.String getIcon() {
		return this.icon;
	}
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setArea(java.lang.String area) {
		this.area = area;
	}
	
	public java.lang.String getArea() {
		return this.area;
	}
	public void setFeature(java.lang.String feature) {
		this.feature = feature;
	}
	
	public java.lang.String getFeature() {
		return this.feature;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}
	
	public java.lang.String getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(java.lang.String addUserId) {
		this.addUserId = addUserId;
	}

	public boolean isCollect() {
		return isCollect;
	}

	public void setCollect(boolean isCollect) {
		this.isCollect = isCollect;
	}

	public boolean isPraise() {
		return isPraise;
	}

	public void setPraise(boolean isPraise) {
		this.isPraise = isPraise;
	}
	public int getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}
	public int getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

}
