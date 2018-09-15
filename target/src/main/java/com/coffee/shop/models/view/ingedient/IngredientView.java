package com.coffee.shop.models.view.ingedient;

import com.coffee.shop.entities.enums.IngredientType;

public class IngredientView {

	private String id;
	
	private String name;
	
	private IngredientType ingredientType;
	
	private double calories;
	
	private double price;
	
	private double quantity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public IngredientType getIngredientType() {
		return ingredientType;
	}

	public void setIngredientType(IngredientType ingredientType) {
		this.ingredientType = ingredientType;
	}
}
