package com.coffee.shop.entities.menuitems;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cold_drinks")
public class ColdDrink extends MenuItem {

	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private double calories;
	
	@Column(nullable = false)
	private double quantity;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}