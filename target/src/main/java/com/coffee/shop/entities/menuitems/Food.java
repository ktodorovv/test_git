package com.coffee.shop.entities.menuitems;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.coffee.shop.entities.enums.FoodType;

@Entity
@Table(name = "foods")
public class Food extends MenuItem {
	
	@Column(nullable = false)
	private FoodType foodType;

	@Column(nullable = false)
	private double calories;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private double quantity;
	
	public FoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}
