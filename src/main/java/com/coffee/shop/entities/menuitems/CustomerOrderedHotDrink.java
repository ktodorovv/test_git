package com.coffee.shop.entities.menuitems;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.coffee.shop.entities.enums.IngredientPortion;

@Entity
@Table(name = "custom_hot_drinks")
public class CustomerOrderedHotDrink extends HotDrink {
	
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private IngredientPortion portion;
	
	private Date dateCreated;

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public IngredientPortion getPortion() {
		return portion;
	}

	public void setPortion(IngredientPortion portion) {
		this.portion = portion;
	}
	
	// TODO: add creator(User class)
}