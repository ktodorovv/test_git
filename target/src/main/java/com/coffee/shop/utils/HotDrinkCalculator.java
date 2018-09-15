package com.coffee.shop.utils;

import com.coffee.shop.constants.EntityConstants;
import com.coffee.shop.entities.enums.IngredientPortion;
import com.coffee.shop.entities.ingredients.Ingredient;
import com.coffee.shop.entities.menuitems.HotDrink;

public class HotDrinkCalculator {

	public double calculateHotDrinkPrice(HotDrink hotDrink) {
		double finalPrice = 0.0;
		finalPrice += hotDrink.getBaseIngredient().getPrice();
		
		for (Ingredient ingredient: hotDrink.getAdditionalIngredients()) {
			finalPrice += ingredient.getPrice();
		}
		
		return finalPrice;
	}
	
	public double calculateHotDrinkCalories(HotDrink hotDrink) {
		double finalCalories = 0.0;
		finalCalories += hotDrink.getBaseIngredient().getCalories();
		
		for (Ingredient ingredient: hotDrink.getAdditionalIngredients()) {
			finalCalories += ingredient.getCalories();
		}
		
		return finalCalories;
	}
	
	public double calculateHotDrinkQuantity(HotDrink hotDrink) {
		double finalQuantity = 0.0;
		finalQuantity += hotDrink.getBaseIngredient().getQuantity();
		
		for (Ingredient ingredient: hotDrink.getAdditionalIngredients()) {
			finalQuantity += ingredient.getQuantity();
		}
		
		return finalQuantity;
	}
	
	private double calculateAccordingToIngredientPortion(double referenceNumberToCalculate, IngredientPortion portion) {
		switch (portion) {
		case SMALL:
			return referenceNumberToCalculate * EntityConstants.SMALL_INGREDIENT_PORTION_MULTIPLIER;
		case MEDIUM:
			return referenceNumberToCalculate * EntityConstants.MEDIUM_INGREDIENT_PORTION_MULTIPLIER;
		case LARGE:
			return referenceNumberToCalculate * EntityConstants.LARGE_INGREDIENT_PORTION_MULTIPLIER;
		default:
			return 0.0;
		}
	}
}