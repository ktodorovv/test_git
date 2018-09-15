package com.coffee.shop.models.view.menuitem;

import java.util.Set;

import com.coffee.shop.entities.enums.FoodType;
import com.coffee.shop.entities.enums.HotDrinkType;
import com.coffee.shop.models.view.ingedient.IngredientView;

public class MenuItemSingleView {
	// TODO: probably should create a few objects out of this one - too big
	
	private String id;
	
	private String name;
	
	private String pictureLink;
	
	private double calories;
	
	private double price;
	
	private double quantity;
	
	private Set<IngredientView> additionalIngredients;
	
	private String type;
	
	private IngredientView baseIngredient;
	
	private FoodType foodType;
	
	private HotDrinkType hotDrinkType;

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

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
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

	public Set<IngredientView> getAdditionalIngredients() {
		return additionalIngredients;
	}

	public void setAdditionalIngredients(Set<IngredientView> additionalIngredients) {
		this.additionalIngredients = additionalIngredients;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public IngredientView getBaseIngredient() {
		return baseIngredient;
	}

	public void setBaseIngredient(IngredientView baseIngredient) {
		this.baseIngredient = baseIngredient;
	}

	public FoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public HotDrinkType getHotDrinkType() {
		return hotDrinkType;
	}

	public void setHotDrinkType(HotDrinkType hotDrinkType) {
		this.hotDrinkType = hotDrinkType;
	}
}