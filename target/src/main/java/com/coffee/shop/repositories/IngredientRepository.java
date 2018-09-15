package com.coffee.shop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coffee.shop.entities.enums.IngredientType;
import com.coffee.shop.entities.ingredients.Ingredient;

@Repository
@Transactional
public interface IngredientRepository extends JpaRepository<Ingredient, String>{

	Ingredient findOneById(String id);
	
	Ingredient findOneByName(String name);
	
	List<Ingredient> findAllByIngredientType(IngredientType ingredientType);
}