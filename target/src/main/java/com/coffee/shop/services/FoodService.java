package com.coffee.shop.services;

import java.util.List;

import com.coffee.shop.models.binding.menuitem.FoodDto;
import com.coffee.shop.models.view.menuitem.MenuItemListView;
import com.coffee.shop.models.view.menuitem.MenuItemSingleView;

public interface FoodService {
	
	List<MenuItemListView> getAll();
	
	MenuItemSingleView getOnyById(String id);
	
	void edit(FoodDto foodDto, String id);
	
	void persist(FoodDto foodDto);
	
	void removeById(String id);
}