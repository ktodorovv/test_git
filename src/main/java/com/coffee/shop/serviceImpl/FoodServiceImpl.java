package com.coffee.shop.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coffee.shop.entities.menuitems.Food;
import com.coffee.shop.models.binding.menuitem.FoodDto;
import com.coffee.shop.models.view.menuitem.MenuItemListView;
import com.coffee.shop.models.view.menuitem.MenuItemSingleView;
import com.coffee.shop.repositories.FoodRepository;
import com.coffee.shop.services.FoodService;
import com.coffee.shop.utils.interfaces.ModelParser;

@Service
public class FoodServiceImpl implements FoodService {

	private final ModelParser modelParser;
	
	private final FoodRepository foodRepo;
	
	public FoodServiceImpl(ModelParser modelParser, FoodRepository foodRepo) {
		this.modelParser = modelParser;
		this.foodRepo = foodRepo;
	}
	
	@Override
	public List<MenuItemListView> getAll() {
		List<Food> foods = this.foodRepo.findAll();
		List<MenuItemListView> foodViews = this.modelParser.convert(foods, MenuItemListView.class);
		
		return foodViews;
	}

	@Override
	public MenuItemSingleView getOnyById(String id) {
		Food food = this.foodRepo.findOneById(id);
		MenuItemSingleView foodView = this.modelParser.convert(food, MenuItemSingleView.class);
		
		return foodView;
	}

	@Override
	public void edit(FoodDto foodDto, String id) {
		Food food = this.modelParser.convert(foodDto, Food.class);
		food.setId(id);
		
		this.foodRepo.save(food);
	}

	@Override
	public void persist(FoodDto foodDto) {
		Food food = this.modelParser.convert(foodDto, Food.class);
		
		this.foodRepo.saveAndFlush(food);
	}

	@Override
	public void removeById(String id) {
		Food food = this.foodRepo.findOneById(id);
		
		this.foodRepo.delete(food);
	}
}