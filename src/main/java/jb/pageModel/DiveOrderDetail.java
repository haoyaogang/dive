package jb.pageModel;

import jb.listener.Application;


public class DiveOrderDetail implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String orderId;	
	private java.lang.String businessId;	
	private java.lang.String businessType;	
	private java.lang.Integer number;	
	private java.lang.Float price;	
	private java.lang.String goodsColor;
	private java.lang.String goodsSize;

	private String businessName;
	private String businessIcon;
	
	public String getBusinessTypeZh() {
		return Application.getString(businessType);
	}
	
	public String getColorZh() {
		return Application.getString(this.goodsColor);
	}
	
	public String getSizeZh() {
		return Application.getString(this.goodsSize);
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
	}
	
	public java.lang.String getOrderId() {
		return this.orderId;
	}
	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}
	
	public java.lang.String getBusinessId() {
		return this.businessId;
	}
	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}
	
	public java.lang.String getBusinessType() {
		return this.businessType;
	}
	public void setNumber(java.lang.Integer number) {
		this.number = number;
	}
	
	public java.lang.Integer getNumber() {
		return this.number;
	}
	public void setPrice(java.lang.Float price) {
		this.price = price;
	}
	
	public java.lang.Float getPrice() {
		return this.price;
	}
	
	public java.lang.String getGoodsColor() {
		return goodsColor;
	}

	public void setGoodsColor(java.lang.String goodsColor) {
		this.goodsColor = goodsColor;
	}

	public java.lang.String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(java.lang.String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	public String getBusinessIcon() {
		return businessIcon;
	}

	public void setBusinessIcon(String businessIcon) {
		this.businessIcon = businessIcon;
	}
}
