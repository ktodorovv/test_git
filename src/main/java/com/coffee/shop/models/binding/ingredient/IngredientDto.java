package com.coffee.shop.models.binding.ingredient;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.coffee.shop.constants.ErrorMessages;
import com.coffee.shop.entities.enums.IngredientType;

public class IngredientDto {
	
	private IngredientType ingredientType;
	
	@NotNull(message = ErrorMessages.BLANK_FIELD_ERROR)
	@NotBlank(message = ErrorMessages.BLANK_FIELD_ERROR)
	@Size(min = 2, max = 30, message = "Size must be between 2 and 30 characters!")
	private String name;
	
	@NotNull(message = ErrorMessages.BLANK_FIELD_ERROR)
	@DecimalMin(value = "0.0", message = ErrorMessages.NO_NUMBER_MESSAGE)
	private double calories;
	
	@NotNull(message = ErrorMessages.BLANK_FIELD_ERROR)
	@DecimalMin(value = "0.0", message = ErrorMessages.NO_NUMBER_MESSAGE)
	private double price;
	
	@NotNull(message = ErrorMessages.BLANK_FIELD_ERROR)
	@DecimalMin(value = "0.0", message = ErrorMessages.NO_NUMBER_MESSAGE)
	private double quantity;

	public IngredientType getIngredientType() {
		return ingredientType;
	}

	public void setIngredientType(IngredientType ingredientType) {
		this.ingredientType = ingredientType;
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
}