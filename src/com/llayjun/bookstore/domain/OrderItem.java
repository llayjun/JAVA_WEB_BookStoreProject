package com.llayjun.bookstore.domain;

public class OrderItem {
	private Orders order;// 订单
	private Products p; // 商品
	private int buynum; // 购物数量
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Products getP() {
		return p;
	}
	public void setP(Products p) {
		this.p = p;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	
	
}
