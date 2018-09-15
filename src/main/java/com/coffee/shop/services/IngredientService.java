package com.coffee.shop.services;

import java.util.List;

import com.coffee.shop.models.binding.ingredient.IngredientDto;
import com.coffee.shop.models.view.ingedient.IngredientView;

public interface IngredientService {
	
	List<IngredientView> getAll();
	
	List<IngredientView> getAllBaseCoffeeIngredients();
	
	List<IngredientView> getAllBaseTeaIngredients();
	
	List<IngredientView> getAllAdditionalCoffeeIngredients();
	
	List<IngredientView> getAllAdditionalTeaIngredients();
	
	IngredientView getOneById(String id);
	
	IngredientView getOneByName(String name);
	
	void editAdditionalIngredient(IngredientDto ingredientDto, String id);
	
	void persist(IngredientDto ingredientDto);
	
	void removeById(String id);
}