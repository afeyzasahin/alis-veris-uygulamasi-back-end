package com.Spring.ShopApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checkout_cart")
public class CheckoutCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String order_id, payment_type, delivery_address;
	long user_id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	Products product;
	// long ;
	int qty;
	double price;
	@Column(updatable = false, insertable = false)
	String order_date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	/*
	 * # id, product_id, qty, price, order_date, user_id, order_id, payment_type,
	 * delivery_address '10', '2', '6', '9150', '2020-01-28 21:52:18', '1', '21144',
	 * 'COD', 'test adding.' '11', '1', '1', '9150', '2020-01-28 21:52:18', '1',
	 * '21144', 'COD', 'test adding.' '12', '2', '1', '18000', '2020-04-04
	 * 23:07:52', '1', '27393', 'COD', 'delivery address' '13', '4', '3', '18000',
	 * '2020-04-04 23:07:52', '1', '27393', 'COD', 'delivery address' '14', '1',
	 * '1', '150', '2020-04-04 23:08:55', '1', '20865', 'COD', 'tsting.' '15', '1',
	 * '1', '1650', '2020-04-04 23:34:04', '1', '16909', 'COD', 'testing address '
	 * '16', '2', '1', '1650', '2020-04-04 23:34:04', '1', '16909', 'COD', 'testing
	 * address ' '17', '2', '6', '9150', '2021-08-03 00:03:42', '1', '10186', 'COD',
	 * 'test adding.' '18', '1', '1', '9150', '2021-08-03 00:03:42', '1', '10186',
	 * 'COD', 'test adding.' '19', '1', '1', '1345', '2021-08-03 04:07:59', '1',
	 * NULL, 'COD', 'test adding.' '20', '1', '2', '300', '2021-08-04 19:29:53',
	 * '10', '12811', 'COD', 'testing address'
	 */
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

}
