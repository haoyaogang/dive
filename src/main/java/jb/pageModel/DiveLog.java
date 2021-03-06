package jb.pageModel;

import jb.listener.Application;

import java.util.Date;
import java.util.List;

public class DiveLog implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String logType;	
	private java.lang.String fileSrc;	
	private java.lang.String accountId;	
	private java.lang.String diveType;	
	private Date diveDate;			
	private java.lang.String weather;	
	private java.lang.Float waterTemperature;	
	private java.lang.Float seeing;	
	private Date inTime;			
	private Date outTime;			
	private java.lang.Float diveHeight;	
	private java.lang.Float diveWeith;	
	private java.lang.Float weatherTemperature;	
	private java.lang.String windPower;	
	private java.lang.Float gasStart;	
	private java.lang.Float gasEnd;	
	private Date addtime;	
	private java.lang.String diveAddress;
	private java.lang.String highGas;
	private java.lang.String sumary;
	private Integer logRead;
	
	private int commentNum; //评论数量
	private int collectNum; //收藏数量
	private int praiseNum;  //赞人数
	
	private boolean isCollect;
	private boolean isPraise;
	
	//评论
	private List<DiveLogComment> commentList;
	
	
	public String getLogTypeZh() {
		return Application.getString(this.logType);
	}
	public String getDiveTypeZh() {
		return Application.getString(this.diveType);
	}
	public String getWeatherZh() {
		return Application.getString(this.weather);
	}
	public String getWindPowerZh() {
		return Application.getString(this.windPower);
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setLogType(java.lang.String logType) {
		this.logType = logType;
	}
	
	public java.lang.String getLogType() {
		return this.logType;
	}
	public void setFileSrc(java.lang.String fileSrc) {
		this.fileSrc = fileSrc;
	}
	
	public java.lang.String getFileSrc() {
		return this.fileSrc;
	}
	public void setAccountId(java.lang.String accountId) {
		this.accountId = accountId;
	}
	
	public java.lang.String getAccountId() {
		return this.accountId;
	}
	public void setDiveType(java.lang.String diveType) {
		this.diveType = diveType;
	}
	
	public java.lang.String getDiveType() {
		return this.diveType;
	}
	public void setDiveDate(Date diveDate) {
		this.diveDate = diveDate;
	}
	
	public Date getDiveDate() {
		return this.diveDate;
	}
	public void setWeather(java.lang.String weather) {
		this.weather = weather;
	}
	
	public java.lang.String getWeather() {
		return this.weather;
	}
	public void setWaterTemperature(java.lang.Float waterTemperature) {
		this.waterTemperature = waterTemperature;
	}
	
	public java.lang.Float getWaterTemperature() {
		return this.waterTemperature;
	}
	public void setSeeing(java.lang.Float seeing) {
		this.seeing = seeing;
	}
	
	public java.lang.Float getSeeing() {
		return this.seeing;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	
	public Date getInTime() {
		return this.inTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	
	public Date getOutTime() {
		return this.outTime;
	}
	public void setDiveHeight(java.lang.Float diveHeight) {
		this.diveHeight = diveHeight;
	}
	
	public java.lang.Float getDiveHeight() {
		return this.diveHeight;
	}
	public void setDiveWeith(java.lang.Float diveWeith) {
		this.diveWeith = diveWeith;
	}
	
	public java.lang.Float getDiveWeith() {
		return this.diveWeith;
	}
	public void setWeatherTemperature(java.lang.Float weatherTemperature) {
		this.weatherTemperature = weatherTemperature;
	}
	
	public java.lang.Float getWeatherTemperature() {
		return this.weatherTemperature;
	}
	public void setWindPower(java.lang.String windPower) {
		this.windPower = windPower;
	}
	
	public java.lang.String getWindPower() {
		return this.windPower;
	}
	public void setGasStart(java.lang.Float gasStart) {
		this.gasStart = gasStart;
	}
	
	public java.lang.Float getGasStart() {
		return this.gasStart;
	}
	public void setGasEnd(java.lang.Float gasEnd) {
		this.gasEnd = gasEnd;
	}
	
	public java.lang.Float getGasEnd() {
		return this.gasEnd;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}

	public java.lang.String getDiveAddress() {
		return diveAddress;
	}

	public void setDiveAddress(java.lang.String diveAddress) {
		this.diveAddress = diveAddress;
	}

	public java.lang.String getHighGas() {
		return highGas;
	}

	public void setHighGas(java.lang.String highGas) {
		this.highGas = highGas;
	}

	public java.lang.String getSumary() {
		return sumary;
	}
	
	public void setSumary(java.lang.String sumary) {
		this.sumary = sumary;
	}

	public Integer getLogRead() {
		return logRead == null ? 0 : logRead;
	}

	public void setLogRead(Integer logRead) {
		this.logRead = logRead;
	}

	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
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
	public List<DiveLogComment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<DiveLogComment> commentList) {
		this.commentList = commentList;
	}
	
	
}
