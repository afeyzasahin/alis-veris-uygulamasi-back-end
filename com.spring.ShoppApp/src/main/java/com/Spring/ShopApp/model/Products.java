package com.Spring.ShopApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")

/*
 * # id name price added_on category_id productImage 1 Lacivert Bağlama Detaylı
 * Örme Bluz 44.99 2019-05-10 00:00:00 1 2 Lila Fermuar Detaylı Bluz 69.99
 * 2019-06-11 00:00:00 1 3 Siyah Yırtık Detaylı Yüksek Bel 139.99 2019-06-11
 * 00:00:00 2 4 Mavi Bel Detaylı Yüksek Bel 90's Wide 129.99 2019-06-11 00:00:00
 * 2 5 Siyah Geniş Kesim Elbise 99.99 2021-08-07 16:13:36 3 6 Yeşil Multi
 * Desenli Sırt Dekolteli 69.99 2021-08-07 16:13:36 3 ... 7 Cali Glow Sneaker
 * 699.99 2021-08-07 16:13:36 4 8 CARINA LIFT Sneaker 556.99 2021-08-07 16:13:36
 * 4 9 Dorika Kolye 39.99 2021-08-07 16:13:36 5 10 Fimo Kolye 9.49 2021-08-07
 * 16:13:36 5 11 Siyah Cut Out Detaylı Mayo 119.99 2021-08-08 22:10:00 6 12
 * Mürdüm Korse Detaylı Mayo 129.99 2021-08-08 22:10:00 6
 */

public class Products {
	@Id
	long id;
	String name, price, added_on, category_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAdded_on() {
		return added_on;
	}

	public void setAdded_on(String added_on) {
		this.added_on = added_on;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

}
