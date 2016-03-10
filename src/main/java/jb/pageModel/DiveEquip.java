package jb.pageModel;

import java.util.Date;
import java.util.List;

import jb.listener.Application;

public class DiveEquip implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private java.lang.String equipIcon;	
	private java.lang.String equipSumary;	
	private java.lang.String equipName;	
	private java.lang.String equipDes;	
	private java.lang.String equipType;	
	private java.lang.Integer saleNum;	
	private java.lang.Float hot;	
	private java.lang.Float price;	
	private java.lang.String status;	
	private java.lang.String equipBrand;	
	private Date addtime;		
	private java.lang.String addUserId;
	private java.lang.String colors;
	private java.lang.String sizes;
	private List<AttrInfo> colorList;
	private List<AttrInfo> sizeList;
	
	private boolean isCollect;
	private int collectNum; //收藏数量
	
	public String getStatusZh() {
		return Application.getString(this.status);
	}
	public String getEquipTypeZh() {
		return Application.getString(this.equipType);
	}
	public String getEquipBrandZh() {
		return Application.getString(this.equipBrand);
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setEquipIcon(java.lang.String equipIcon) {
		this.equipIcon = equipIcon;
	}
	
	public java.lang.String getEquipIcon() {
		return this.equipIcon;
	}
	public void setEquipSumary(java.lang.String equipSumary) {
		this.equipSumary = equipSumary;
	}
	
	public java.lang.String getEquipSumary() {
		return this.equipSumary;
	}
	public void setEquipName(java.lang.String equipName) {
		this.equipName = equipName;
	}
	
	public java.lang.String getEquipName() {
		return this.equipName;
	}
	public void setEquipDes(java.lang.String equipDes) {
		this.equipDes = equipDes;
	}
	
	public java.lang.String getEquipDes() {
		return this.equipDes;
	}
	public void setEquipType(java.lang.String equipType) {
		this.equipType = equipType;
	}
	
	public java.lang.String getEquipType() {
		return this.equipType;
	}
	public void setSaleNum(java.lang.Integer saleNum) {
		this.saleNum = saleNum;
	}
	
	public java.lang.Integer getSaleNum() {
		return this.saleNum;
	}
	public void setHot(java.lang.Float hot) {
		this.hot = hot;
	}
	
	public java.lang.Float getHot() {
		return this.hot;
	}
	public void setPrice(java.lang.Float price) {
		this.price = price;
	}
	
	public java.lang.Float getPrice() {
		return this.price;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setEquipBrand(java.lang.String equipBrand) {
		this.equipBrand = equipBrand;
	}
	
	public java.lang.String getEquipBrand() {
		return this.equipBrand;
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

	public java.lang.String getColors() {
		return colors;
	}
	public void setColors(java.lang.String colors) {
		this.colors = colors;
	}
	public java.lang.String getSizes() {
		return sizes;
	}
	public void setSizes(java.lang.String sizes) {
		this.sizes = sizes;
	}
	public boolean isCollect() {
		return isCollect;
	}

	public void setCollect(boolean isCollect) {
		this.isCollect = isCollect;
	}
	public int getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}
	public List<AttrInfo> getColorList() {
		return colorList;
	}
	public void setColorList(List<AttrInfo> colorList) {
		this.colorList = colorList;
	}
	public List<AttrInfo> getSizeList() {
		return sizeList;
	}
	public void setSizeList(List<AttrInfo> sizeList) {
		this.sizeList = sizeList;
	}

}
