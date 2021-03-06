package jb.pageModel;

import java.util.Date;
import java.util.List;

import jb.listener.Application;

public class DiveOrder implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String orderNo;
	private java.lang.String accountId;	
	private java.lang.String address;	
	private java.lang.String expressName;	
	private java.lang.String expressNo;	
	private java.lang.String payWay;	
	private java.lang.String remark;	
	private java.lang.String payStatus;	
	private java.lang.String orderStatus;	
	private java.lang.String sendStatus;	
	private Date paytime;			
	private Date addtime;	
	private java.lang.Float amount;	// 订单总金额（元）
	private java.lang.String channel;

	private List<DiveOrderDetail> detail_list;
	
	private String addUserId_travel;
	private String addUserId_equip;
	
	private String userName;
	private String nickname;
	
	private Date paytimeBegin;
	private Date paytimeEnd;
	
	public String getOrderStatusZh() {
		return Application.getString(orderStatus);
	}
	
	public String getPayStatusZh() {
		return Application.getString(payStatus);
	}
	
	public String getSendStatusZh() {
		return Application.getString(sendStatus);
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(java.lang.String orderNo) {
		this.orderNo = orderNo;
	}

	public void setAccountId(java.lang.String accountId) {
		this.accountId = accountId;
	}
	
	public java.lang.String getAccountId() {
		return this.accountId;
	}
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setExpressName(java.lang.String expressName) {
		this.expressName = expressName;
	}
	
	public java.lang.String getExpressName() {
		return this.expressName;
	}
	public void setExpressNo(java.lang.String expressNo) {
		this.expressNo = expressNo;
	}
	
	public java.lang.String getExpressNo() {
		return this.expressNo;
	}
	public void setPayWay(java.lang.String payWay) {
		this.payWay = payWay;
	}
	
	public java.lang.String getPayWay() {
		return this.payWay;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setPayStatus(java.lang.String payStatus) {
		this.payStatus = payStatus;
	}
	
	public java.lang.String getPayStatus() {
		return this.payStatus;
	}
	public void setOrderStatus(java.lang.String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public java.lang.String getOrderStatus() {
		return this.orderStatus;
	}
	public void setSendStatus(java.lang.String sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	public java.lang.String getSendStatus() {
		return this.sendStatus;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	
	public Date getPaytime() {
		return this.paytime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}

	public java.lang.Float getAmount() {
		return amount;
	}

	public void setAmount(java.lang.Float amount) {
		this.amount = amount;
	}

	public java.lang.String getChannel() {
		return channel;
	}

	public void setChannel(java.lang.String channel) {
		this.channel = channel;
	}

	public List<DiveOrderDetail> getDetail_list() {
		return detail_list;
	}

	public void setDetail_list(List<DiveOrderDetail> detail_list) {
		this.detail_list = detail_list;
	}

	public String getAddUserId_travel() {
		return addUserId_travel;
	}

	public void setAddUserId_travel(String addUserId_travel) {
		this.addUserId_travel = addUserId_travel;
	}

	public String getAddUserId_equip() {
		return addUserId_equip;
	}

	public void setAddUserId_equip(String addUserId_equip) {
		this.addUserId_equip = addUserId_equip;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getPaytimeBegin() {
		return paytimeBegin;
	}

	public void setPaytimeBegin(Date paytimeBegin) {
		this.paytimeBegin = paytimeBegin;
	}

	public Date getPaytimeEnd() {
		return paytimeEnd;
	}

	public void setPaytimeEnd(Date paytimeEnd) {
		this.paytimeEnd = paytimeEnd;
	}
}
