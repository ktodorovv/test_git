package com.coffee.shop.models.binding.menuitem;

import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.coffee.shop.constants.ErrorMessages;
import com.coffee.shop.entities.enums.HotDrinkType;

public class HotDrinkDto {

	@NotNull(message = ErrorMessages.BLANK_FIELD_ERROR)
	@NotBlank(message = ErrorMessages.BLANK_FIELD_ERROR)
	@Size(min = 1, max = 40, message = "Size must be between 1 and 40 chars!")
	private String name;
	
	private String pictureLink;
	
	private HotDrinkType type;
	
	private String baseIngredient;
	
	private Set<String> additionalIngredients;

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

	public HotDrinkType getType() {
		return type;
	}

	public void setType(HotDrinkType type) {
		this.type = type;
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
