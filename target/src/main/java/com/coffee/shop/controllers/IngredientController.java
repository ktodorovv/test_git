package com.coffee.shop.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coffee.shop.entities.enums.IngredientType;
import com.coffee.shop.models.binding.ingredient.IngredientDto;
import com.coffee.shop.models.view.ingedient.IngredientView;
import com.coffee.shop.services.IngredientService;

@Controller
@RequestMapping("/ingredients")
public class IngredientController extends BaseController {

	private final IngredientService ingredientService;
	
	@Autowired
	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}
	
	@GetMapping("/all")
	public ModelAndView getAllIngredients() {
		List<IngredientView> ingredients = this.ingredientService.getAll();
		
		return super.view("/ingredients/all-ingredients", "ingredients", ingredients);
	}
	
	@GetMapping("/add")
	public ModelAndView getAddIngredient(@ModelAttribute IngredientDto ingredientDto) {		
		return super.view("ingredients/add-ingredient", "types", IngredientType.values());
	}
	
	@PostMapping("/add")
	public ModelAndView postAddIngredient(@Valid @ModelAttribute IngredientDto ingredientDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return super.view("ingredients/add-ingredient", "types", IngredientType.values());
		}
		
		this.ingredientService.persist(ingredientDto);
		
		return super.redirect("/ingredients/add");
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView getEditIngredient(@PathVariable String id) {
		IngredientView ingredient = this.ingredientService.getOneById(id);
		
		return super.view("/ingredients/edit-ingredient", "ingredientDto", ingredient);
	}
	
	@PostMapping("/edit/{id}")
	public ModelAndView postEditIngredient(@Valid @ModelAttribute IngredientDto ingredient, BindingResult bindingResult, @PathVariable String id) {
		if (bindingResult.hasErrors()) {
//			IngredientView ingredient = this.ingredientService.getOneById(id);
			
			return super.view("/ingredients/edit-ingredient");
		}
		
		this.ingredientService.editAdditionalIngredient(ingredient, id);
		
		return super.redirect("/ingredients/all");
	}
	
	@PostMapping("/delete/{id}")
	public ModelAndView postDeleteIngredient(@PathVariable String id) {
		this.ingredientService.removeById(id);
		
		return super.redirect("/ingredients/all");
	}
}
