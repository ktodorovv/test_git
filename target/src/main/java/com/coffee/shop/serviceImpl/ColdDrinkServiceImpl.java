package com.coffee.shop.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coffee.shop.entities.menuitems.ColdDrink;
import com.coffee.shop.models.binding.menuitem.ColdDrinkDto;
import com.coffee.shop.models.view.menuitem.MenuItemListView;
import com.coffee.shop.models.view.menuitem.MenuItemSingleView;
import com.coffee.shop.repositories.ColdDrinkRepository;
import com.coffee.shop.services.ColdDrinkService;
import com.coffee.shop.utils.interfaces.ModelParser;

@Service
public class ColdDrinkServiceImpl implements ColdDrinkService {

	private final ModelParser modelParser;
	
	private final ColdDrinkRepository coldDrinkRepo;
	
	public ColdDrinkServiceImpl(ModelParser modelParser, ColdDrinkRepository coldDrinkRepo) {
		this.modelParser = modelParser;
		this.coldDrinkRepo = coldDrinkRepo;
	}
	
	@Override
	public List<MenuItemListView> getAll() {
		List<ColdDrink> menuItems = this.coldDrinkRepo.findAll();
		List<MenuItemListView> menuItemListViews = this.modelParser.convert(menuItems, MenuItemListView.class);
		
		return menuItemListViews;
	}

	@Override
	public MenuItemSingleView getOnyById(String id) {
		ColdDrink coldDrink = this.coldDrinkRepo.findOneById(id);
		MenuItemSingleView coldDrinkView = this.modelParser.convert(coldDrink, MenuItemSingleView.class);
		
		return coldDrinkView;
	}

	@Override
	public void edit(ColdDrinkDto coldDrinkDto, String id) {
		ColdDrink coldDrink = this.modelParser.convert(coldDrinkDto, ColdDrink.class);
		coldDrink.setId(id);
		
		this.coldDrinkRepo.save(coldDrink);
	}

	@Override
	public void persist(ColdDrinkDto coldDrinkDto) {
		ColdDrink coldDrink = this.modelParser.convert(coldDrinkDto, ColdDrink.class);
		
		this.coldDrinkRepo.saveAndFlush(coldDrink);
	}

	@Override
	public void removeById(String id) {
		ColdDrink coldDrink = this.coldDrinkRepo.findOneById(id);
		
		this.coldDrinkRepo.delete(coldDrink);
	}
}