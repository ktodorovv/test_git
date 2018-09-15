package com.coffee.shop.models.binding.menuitem;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.coffee.shop.constants.ErrorMessages;
import com.coffee.shop.entities.enums.FoodType;

public class FoodDto {
	
	@NotNull(message = ErrorMessages.BLANK_FIELD_ERROR)
	@NotBlank(message = ErrorMessages.BLANK_FIELD_ERROR)
	@Size(min = 1, max = 40, message = "Size must be between 1 and 40 chars!")
	private String name;
	
	private String pictureLink;
	
	private FoodType foodType;
	
	@NotNull(message = ErrorMessages.BLANK_FIELD_ERROR)
	@DecimalMin(value = "0.0", message = ErrorMessages.NO_NUMBER_MESSAGE)
	private double calories;
	
	@NotNull(message = ErrorMessages.BLANK_FIELD_ERROR)
	@DecimalMin(value = "0.0", message = ErrorMessages.NO_NUMBER_MESSAGE)
	private double quantity;
	
	@NotNull(message = ErrorMessages.BLANK_FIELD_ERROR)
	@DecimalMin(value = "0.0", message = ErrorMessages.NO_NUMBER_MESSAGE)
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

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

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
