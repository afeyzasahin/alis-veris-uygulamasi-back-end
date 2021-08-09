package com.Spring.ShopApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="category")

/*# id, name
'1', 'Üst Giyim'
'2', 'Alt Giyim'
'3', 'Elbise'
'4', 'Ayakkabı'
'5', 'Takı'
'6', 'Mayo-Bikini'
*/

public class Category {
	@Id
	long id;
	String name;
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
	
}
