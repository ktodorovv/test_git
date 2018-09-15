package com.coffee.shop.services;

import java.util.List;

import com.coffee.shop.models.binding.menuitem.ColdDrinkDto;
import com.coffee.shop.models.view.menuitem.MenuItemListView;
import com.coffee.shop.models.view.menuitem.MenuItemSingleView;

public interface ColdDrinkService {
	
	List<MenuItemListView> getAll();
	
	MenuItemSingleView getOnyById(String id);
	
	void edit(ColdDrinkDto coldDrinkDto, String id);
	
	void persist(ColdDrinkDto coldDrinkDto);
	
	void removeById(String id);
}