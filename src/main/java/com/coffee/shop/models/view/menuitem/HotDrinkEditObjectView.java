package com.coffee.shop.models.view.menuitem;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.coffee.shop.constants.ErrorMessages;
import com.coffee.shop.models.view.ingedient.IngredientView;

public class HotDrinkEditObjectView {
	
	private List<IngredientView> allBaseIngredients;
	
	private Map<IngredientView, Boolean> allAdditionalIngredients;

	@NotNull(message = ErrorMessages.BLANK_FIELD_ERROR)
	@NotBlank(message = ErrorMessages.BLANK_FIELD_ERROR)
	@Size(min = 1, max = 40, message = "Size must be between 1 and 40 chars!")
	private String name;
	
	private String pictureLink;
	
	private String baseIngredient;
	
	private Set<String> additionalIngredients;

	public List<IngredientView> getAllBaseIngredients() {
		return allBaseIngredients;
	}

	public void setAllBaseIngredients(List<IngredientView> allBaseIngredients) {
		this.allBaseIngredients = allBaseIngredients;
	}

	public Map<IngredientView, Boolean> getAllAdditionalIngredients() {
		return allAdditionalIngredients;
	}

	public void setAllAdditionalIngredients(Map<IngredientView, Boolean> allAdditionalIngredients) {
		this.allAdditionalIngredients = allAdditionalIngredients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public String getBaseIngredient() {
		return baseIngredient;
	}

	public void setBaseIngredient(String baseIngredient) {
		this.baseIngredient = baseIngredient;
	}

	public Set<String> getAdditionalIngredients() {
		return additionalIngredients;
	}

	public void setAdditionalIngredients(Set<String> additionalIngredients) {
		this.additionalIngredients = additionalIngredients;
	}
}