package com.coffee.shop.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.coffee.shop.entities.enums.HotDrinkType;
import com.coffee.shop.entities.ingredients.Ingredient;
import com.coffee.shop.entities.menuitems.HotDrink;
import com.coffee.shop.models.binding.menuitem.HotDrinkDto;
import com.coffee.shop.models.view.ingedient.IngredientView;
import com.coffee.shop.models.view.menuitem.HotDrinkEditObjectView;
import com.coffee.shop.models.view.menuitem.MenuItemListView;
import com.coffee.shop.models.view.menuitem.MenuItemSingleView;
import com.coffee.shop.repositories.HotDrinkRepository;
import com.coffee.shop.services.HotDrinkService;
import com.coffee.shop.services.IngredientService;
import com.coffee.shop.utils.HotDrinkCalculator;
import com.coffee.shop.utils.interfaces.ModelParser;

@Service
public class HotDrinkServiceImpl implements HotDrinkService {

	private final ModelParser modelParser;
	private final HotDrinkRepository hotDrinkRepo;
	private final IngredientService ingredientService;
	private final HotDrinkCalculator hotDrinkCalc;
	
	public HotDrinkServiceImpl(ModelParser modelParser, HotDrinkRepository hotDrinkRepo, HotDrinkCalculator hotDrinkCalc, IngredientService ingredientService) {
		this.modelParser = modelParser;
		this.hotDrinkRepo = hotDrinkRepo;
		this.hotDrinkCalc = hotDrinkCalc;
		this.ingredientService = ingredientService;
	}
	
	@Override
	public List<MenuItemListView> getAll() {
		List<HotDrink> hotDrinks = this.hotDrinkRepo.findAll();
		List<MenuItemListView> hotDrinkViews = this.convertAllHotDrinks(hotDrinks);
		
		return hotDrinkViews;
	}

	@Override
	public List<MenuItemListView> getAllTeas() {
		List<HotDrink> teas = this.hotDrinkRepo.findAllByType(HotDrinkType.TEA);
		List<MenuItemListView> teaViews = this.convertAllHotDrinks(teas);
		
		return teaViews;
	}

	@Override
	public List<MenuItemListView> getAllCoffees() {
		List<HotDrink> coffees = this.hotDrinkRepo.findAllByType(HotDrinkType.COFFEE);
		List<MenuItemListView> coffeeViews = this.convertAllHotDrinks(coffees);
		
		return coffeeViews;
	}

	@Override
	public MenuItemSingleView getOneById(String id) {
		HotDrink hotDrink = this.hotDrinkRepo.findOneById(id);
		MenuItemSingleView hotDrinkView = this.modelParser.convert(hotDrink, MenuItemSingleView.class);
		hotDrinkView.setCalories(this.hotDrinkCalc.calculateHotDrinkCalories(hotDrink));
		hotDrinkView.setQuantity(this.hotDrinkCalc.calculateHotDrinkQuantity(hotDrink));
		hotDrinkView.setPrice(this.hotDrinkCalc.calculateHotDrinkPrice(hotDrink));
		
		return hotDrinkView;
	}

	@Override
	public void edit(HotDrinkEditObjectView hotDrinkEditObjectView, String id) {
		HotDrinkDto hotDrinkDto = this.turnHotDrinkEditObjectViewToDto(hotDrinkEditObjectView);
		HotDrinkType type = this.getHotDrinkTypeById(id);
		hotDrinkDto.setType(type);
		HotDrink hotDrink = this.mapHotDrinkDtoToEntity(hotDrinkDto);
		hotDrink.setId(id);
		
		this.hotDrinkRepo.save(hotDrink);
	}
	
	private HotDrinkDto turnHotDrinkEditObjectViewToDto(HotDrinkEditObjectView hotDrinkEditObjectView) {
		HotDrinkDto hotDrinkDto = this.modelParser.convert(hotDrinkEditObjectView, HotDrinkDto.class);
		
		return hotDrinkDto;
	}

	@Override
	public void persist(HotDrinkDto hotDrinkDto, HotDrinkType type) {
		hotDrinkDto.setType(type);
		HotDrink hotDrink = this.mapHotDrinkDtoToEntity(hotDrinkDto);
		
		this.hotDrinkRepo.saveAndFlush(hotDrink);
	}

	@Override
	public void remove(String id) {
		HotDrink hotDrink = this.hotDrinkRepo.findOneById(id);
		
		this.hotDrinkRepo.delete(hotDrink);
	}

	private List<MenuItemListView> convertAllHotDrinks(List<HotDrink> drinks) {
		List<MenuItemListView> hotDrinkViews = new ArrayList<>();
		for (HotDrink hotDrink : drinks) {
			MenuItemListView hotDrinkView = this.modelParser.convert(hotDrink, MenuItemListView.class);
			hotDrinkView.setPrice(this.hotDrinkCalc.calculateHotDrinkPrice(hotDrink));
			hotDrinkViews.add(hotDrinkView);
		}
		
		return hotDrinkViews;
	}
	
	// TODO: think of a more elegant solution
	private HotDrink mapHotDrinkDtoToEntity(HotDrinkDto hotDrinkDto) {
		String baseIngredientStr = hotDrinkDto.getBaseIngredient();
		IngredientView baseIngredientView = this.ingredientService.getOneByName(baseIngredientStr);
		Set<String> additionalIngredientsStr = hotDrinkDto.getAdditionalIngredients();
		Set<IngredientView> additionalIngredientViews = new HashSet<>();
		for (String ing : additionalIngredientsStr) {
			IngredientView additionalIngredientView = this.ingredientService.getOneByName(ing);
			additionalIngredientViews.add(additionalIngredientView);
		}
		
		Ingredient baseIngredient = this.modelParser.convert(baseIngredientView, Ingredient.class);
		Set<Ingredient> additionalIngredients = this.modelParser.convert(additionalIngredientViews, Ingredient.class);
		
		HotDrink hotDrink = this.modelParser.convert(hotDrinkDto, HotDrink.class);
		hotDrink.setBaseIngredient(baseIngredient);
		hotDrink.setAdditionalIngredients(additionalIngredients);
		
		return hotDrink;
	}

	// TODO: better solution?
	@Override
	public HotDrinkEditObjectView getOneForEditHotDrink(String id) {
		HotDrinkType type = this.getHotDrinkTypeById(id);
		MenuItemSingleView menuItem = this.getOneById(id);
		List<IngredientView> baseIngredients = null;
		List<IngredientView> additionalIngredients = null;
		if (type == HotDrinkType.TEA) {
			baseIngredients = this.ingredientService.getAllBaseTeaIngredients();
			additionalIngredients = this.ingredientService.getAllAdditionalTeaIngredients();
		} else {
			baseIngredients = this.ingredientService.getAllBaseCoffeeIngredients();
			additionalIngredients = this.ingredientService.getAllAdditionalCoffeeIngredients();
		}
		
		HotDrinkEditObjectView hotDrinkEditObjectView = new HotDrinkEditObjectView();
		hotDrinkEditObjectView.setAllBaseIngredients(baseIngredients);
		hotDrinkEditObjectView.setAllAdditionalIngredients(this.generateAdditionalIngredientsMap(menuItem.getAdditionalIngredients(), additionalIngredients));
		hotDrinkEditObjectView.setName(menuItem.getName());
		hotDrinkEditObjectView.setPictureLink(menuItem.getPictureLink());
		hotDrinkEditObjectView.setAdditionalIngredients(this.turnIngredientViewsToStrings(menuItem.getAdditionalIngredients()));
		hotDrinkEditObjectView.setBaseIngredient(menuItem.getBaseIngredient().getName());
		
		return hotDrinkEditObjectView;
	}
	
	// TODO: too much for a simple thing
	private Set<String> turnIngredientViewsToStrings(Set<IngredientView> ingredientViews) {
		Set<String> ingredientStrings = new HashSet<>();
		for (IngredientView ingredientView : ingredientViews) {
			ingredientStrings.add(ingredientView.getName());
		}
		
		return ingredientStrings;
	}

	@Override
	public HotDrinkType getHotDrinkTypeById(String id) {
		HotDrink hotDrink = this.hotDrinkRepo.findOneById(id);
		HotDrinkType type =  hotDrink.getType();
		
		return type;
	}
	
	// TODO: better solution?
	private Map<IngredientView, Boolean> generateAdditionalIngredientsMap(Set<IngredientView> additionalIngredientsForItem, List<IngredientView> ingredientList) {
		SortedMap<IngredientView, Boolean> ingredientMap = new TreeMap<>(new Comparator<IngredientView>() {
			@Override
			public int compare(IngredientView o1, IngredientView o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		for (IngredientView ingredient : ingredientList) {
			boolean checked = false;
			for (IngredientView itemIngredient : additionalIngredientsForItem ) {
				if (ingredient.getName().equals(itemIngredient.getName())) {
					checked = true;
					break;
				}
			}
			
			ingredientMap.put(ingredient, checked);
		}
		
		return ingredientMap;
	}

	// TODO: avoid code repetition - in this one and in the getOneForEditHotDrink there are a lot of things that are repeated
	@Override
	public void addAllAdditionalAndBaseIngredientsForEditHotDrink(HotDrinkEditObjectView hotDrinkEditObjectView, String id) {
		HotDrinkType type = this.getHotDrinkTypeById(id);
		MenuItemSingleView menuItem = this.getOneById(id);
		List<IngredientView> baseIngredients = null;
		List<IngredientView> additionalIngredients = null;
		if (type == HotDrinkType.TEA) {
			baseIngredients = this.ingredientService.getAllBaseTeaIngredients();
			additionalIngredients = this.ingredientService.getAllAdditionalTeaIngredients();
		} else {
			baseIngredients = this.ingredientService.getAllBaseCoffeeIngredients();
			additionalIngredients = this.ingredientService.getAllAdditionalCoffeeIngredients();
		}
		
		Map<IngredientView, Boolean> allAdditionalIngredients = this.generateAdditionalIngredientsMap(menuItem.getAdditionalIngredients(), additionalIngredients);
		
		hotDrinkEditObjectView.setAllAdditionalIngredients(allAdditionalIngredients);
		hotDrinkEditObjectView.setAllBaseIngredients(baseIngredients);
	}
}