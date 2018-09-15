package com.coffee.shop.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coffee.shop.entities.enums.FoodType;
import com.coffee.shop.entities.enums.HotDrinkType;
import com.coffee.shop.models.binding.menuitem.ColdDrinkDto;
import com.coffee.shop.models.binding.menuitem.FoodDto;
import com.coffee.shop.models.binding.menuitem.HotDrinkDto;
import com.coffee.shop.models.view.ingedient.IngredientView;
import com.coffee.shop.models.view.menuitem.HotDrinkEditObjectView;
import com.coffee.shop.models.view.menuitem.MenuItemListView;
import com.coffee.shop.models.view.menuitem.MenuItemSingleView;
import com.coffee.shop.services.ColdDrinkService;
import com.coffee.shop.services.FoodService;
import com.coffee.shop.services.HotDrinkService;
import com.coffee.shop.services.IngredientService;

@Controller
@RequestMapping("/menu")
public class MenuItemController extends BaseController {
	
	private final ColdDrinkService coldDrinkService;
	
	private final FoodService foodService;
	
	private final HotDrinkService hotDrinkService;
	
	private final IngredientService ingredientService;
	
	@Autowired
	public MenuItemController(ColdDrinkService coldDrinkService, FoodService foodService, HotDrinkService hotDrinkService, IngredientService ingredientService) {
		this.coldDrinkService = coldDrinkService;
		this.foodService = foodService;
		this.hotDrinkService = hotDrinkService;
		this.ingredientService = ingredientService;
	}
	
	@GetMapping("/cold-drinks/add")
	public ModelAndView getAddColdDrink(@ModelAttribute ColdDrinkDto coldDrinkDto) {
		return super.view("menu/cold-drinks/add-cold-drink");
	}
	
	@PostMapping("/cold-drinks/add")
	public ModelAndView postAddColdDrink(@Valid @ModelAttribute ColdDrinkDto coldDrinkDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return super.view("menu/cold-drinks/add-cold-drink");
		}
		
		this.coldDrinkService.persist(coldDrinkDto);
		
		return super.redirect("/menu/cold-drinks/add");
	}
	
	@GetMapping("/cold-drinks/edit/{id}")
	public ModelAndView getEditColdDrink(@PathVariable String id) {
		MenuItemSingleView menuItem = this.coldDrinkService.getOnyById(id);
		
		return super.view("menu/cold-drinks/edit-cold-drink", "coldDrinkDto", menuItem);
	}
	
	@PostMapping("/cold-drinks/edit/{id}")
	public ModelAndView postEditColdDrink(@Valid @ModelAttribute ColdDrinkDto coldDrinkDto, BindingResult bindingResult, @PathVariable String id) {
		if (bindingResult.hasErrors()) {
			return super.view("menu/cold-drinks/edit-cold-drink", "coldDrinkDto", coldDrinkDto);
		}
		
		this.coldDrinkService.edit(coldDrinkDto, id);
		
		return super.redirect("/menu/cold-drinks/all");
	}
	
	@GetMapping("/cold-drinks/all")
	public ModelAndView getAllColdDrinks() {
		List<MenuItemListView> coldDrinks = this.coldDrinkService.getAll();
		
		return super.view("/menu/cold-drinks/all-cold-drinks", "coldDrinks", coldDrinks);
	}
	
	@GetMapping("/cold-drinks/{id}")
	public ModelAndView getOneColdDrink(@PathVariable String id) {
		MenuItemSingleView menuItem = this.coldDrinkService.getOnyById(id);
		
		return super.view("menu/individual-menu-item-view", "menuItem", menuItem);
	}
	
	@PostMapping("/cold-drinks/delete/{id}")
	public ModelAndView postDeleteColdDrink(@PathVariable String id) {
		this.coldDrinkService.removeById(id);
		
		return super.redirect("/menu/cold-drinks/all");
	}
	
	@GetMapping("/food/all")
	public ModelAndView getAllFoods() {
		List<MenuItemListView> foods = this.foodService.getAll();
		
		return super.view("menu/food/all-foods", "foods", foods);
	}
	
	@GetMapping("/food/add")
	public ModelAndView getAddFood(@ModelAttribute FoodDto foodDto) {
		return super.view("menu/food/add-food", "foodTypes", FoodType.values());
	}
	
	@PostMapping("/food/add")
	public ModelAndView postAddFood(@Valid @ModelAttribute FoodDto foodDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return super.view("menu/food/add-food");
		}
		
		this.foodService.persist(foodDto);
		
		return super.redirect("/menu/food/add");
	}
	
	@GetMapping("/food/edit/{id}")
	public ModelAndView getEditFood(@PathVariable String id) {
		MenuItemSingleView food = this.foodService.getOnyById(id);
		
		return super.view("menu/food/edit-food", "foodDto", food);
	}
	
	@PostMapping("/food/edit/{id}")
	public ModelAndView postEditFood(@Valid @ModelAttribute FoodDto foodDto, BindingResult bindingResult, @PathVariable String id) {
		if (bindingResult.hasErrors()) {
			return super.view("menu/food/edit-food");
		}
		
		this.foodService.edit(foodDto, id);
		
		return super.redirect("/menu/food/all");
	}
	
	@GetMapping("/food/{id}")
	public ModelAndView getOneFood(@PathVariable String id) {
		MenuItemSingleView food = this.foodService.getOnyById(id);
		
		return super.view("menu/individual-menu-item-view", "menuItem", food);
	}
	
	@PostMapping("/food/delete/{id}")
	public ModelAndView postDeleteFood(@PathVariable String id) {
		this.foodService.removeById(id);
		
		return super.redirect("/menu/food/all");
	}
		
	// HOT DRINKS
	// TODO: probably unite all the list views - all teas, all coffees, all foods, all cold drinks.
	// TODO: Probably use some interceptor, in order to let them know if we're adding tea, coffee, food or a cold drink.
	
	@GetMapping("/hot-drinks/{id}")
	public ModelAndView getOneHotDrink(@PathVariable String id) {
		MenuItemSingleView hotDrink = this.hotDrinkService.getOneById(id);
		
		return super.view("menu/individual-menu-item-view", "menuItem", hotDrink);
	}
	
	@GetMapping("/hot-drinks/tea")
	public ModelAndView getAllTeas() {
		List<MenuItemListView> teas = this.hotDrinkService.getAllTeas();
		
		return super.view("menu/hot-drinks/all-hot-drinks", "hotDrinks", teas);
	}
	
	@GetMapping("/hot-drinks/coffee")
	public ModelAndView getAllCoffees() {
		List<MenuItemListView> coffees = this.hotDrinkService.getAllCoffees();
	
		return super.view("menu/hot-drinks/all-hot-drinks", "hotDrinks", coffees);
	}
	
	// TODO: why did i use separate templates for adding coffee and tea?
	@GetMapping("/hot-drinks/coffee/add")
	public ModelAndView getAddCoffee(@ModelAttribute HotDrinkDto hotDrinkDto) {
		Map<String, List<IngredientView>> ingredients = new HashMap<>();
		List<IngredientView> baseIngredients = this.ingredientService.getAllBaseCoffeeIngredients();
		List<IngredientView> additionalIngredients = this.ingredientService.getAllAdditionalCoffeeIngredients();
		ingredients.put("base", baseIngredients);
		ingredients.put("additional", additionalIngredients);
		
		return super.view("menu/hot-drinks/add-coffee", "ingredients", ingredients);
	}
	
	@GetMapping("/hot-drinks/tea/add")
	public ModelAndView getAddTea(@ModelAttribute HotDrinkDto hotDrinkDto) {
		Map<String, List<IngredientView>> ingredients = new HashMap<>();
		List<IngredientView> baseIngredients = this.ingredientService.getAllBaseTeaIngredients();
		List<IngredientView> additionalIngredients = this.ingredientService.getAllAdditionalTeaIngredients();
		ingredients.put("base", baseIngredients);
		ingredients.put("additional", additionalIngredients);
		
		return super.view("menu/hot-drinks/add-coffee", "ingredients", ingredients);
	}
	
	@PostMapping("/hot-drinks/coffee/add")
	public ModelAndView postAddCoffee(@Valid @ModelAttribute HotDrinkDto hotDrinkDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return this.getAddCoffee(hotDrinkDto);
		}
		
		this.hotDrinkService.persist(hotDrinkDto, HotDrinkType.COFFEE);
		
		return super.redirect("/menu/hot-drinks/coffee/add");
	}
	
	@PostMapping("/hot-drinks/tea/add")
	public ModelAndView postAddTea(@Valid @ModelAttribute HotDrinkDto hotDrinkDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return this.getAddTea(hotDrinkDto);
		}
		
		this.hotDrinkService.persist(hotDrinkDto, HotDrinkType.TEA);
		
		return super.redirect("/menu/hot-drinks/tea/add");
	}
	
	@GetMapping("/hot-drinks/edit/{id}")
	public ModelAndView getEditHotDrink(@PathVariable String id, @ModelAttribute HotDrinkEditObjectView hotDrinkEditObjectView) {
		if (hotDrinkEditObjectView.getName() == null) {
			hotDrinkEditObjectView = this.hotDrinkService.getOneForEditHotDrink(id);
		}
		
		return super.view("menu/hot-drinks/edit-hot-drink", "hotDrinkEditObjectView", hotDrinkEditObjectView);
	}
	
	@PostMapping("/hot-drinks/edit/{id}")
	public ModelAndView postEditHotDrink(@Valid @ModelAttribute HotDrinkEditObjectView hotDrinkEditObjectView, BindingResult bindingResult, @PathVariable String id) {
		if (bindingResult.hasErrors()) {
			this.hotDrinkService.addAllAdditionalAndBaseIngredientsForEditHotDrink(hotDrinkEditObjectView, id);
			return this.getEditHotDrink(id, hotDrinkEditObjectView);
		}
		
		// TODO: should not be looking for the type in the controller?
		String type = this.hotDrinkService.getHotDrinkTypeById(id).toString().toLowerCase();
		this.hotDrinkService.edit(hotDrinkEditObjectView, id);
		
		return super.redirect("/menu/hot-drinks/" + type);
	}
	
	@PostMapping("/hot-drinks/delete/{id}")
	public ModelAndView postDeleteHotDrink(@PathVariable String id) {
		this.hotDrinkService.remove(id);
		
		// TODO: should redirect to all teas and all coffees respectively
		return super.redirect("/home");
	}
}